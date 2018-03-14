//
//  RestApi.swift
//
//
//  Created by Russell Wolf on 2/18/18.
//

import Foundation
import common

class RestClient: NSObject, CommonRestApi {
    let webClient = WebClient()
    
    func getMyIp() -> CommonCall {
        let url = "https://api.ipify.org/?format=json"
        let modelCreator = { (data: Data?) -> CommonIpModel? in
            guard let responseData = data else {
                return nil
            }
            do {
                guard let jsonData = try JSONSerialization.jsonObject(with: responseData) as? [String: Any] else {
                    return nil
                }
                guard let ip = jsonData["ip"] as? String else {
                    return nil
                }
                let ipModel = CommonIpModel()
                ipModel.ip = ip
                return ipModel
            } catch {
                return nil
            }
        }
        return Call(_webClient: webClient, _url: url, _modelCreator: modelCreator)
    }
}

class WebClient {
    // TODO report errors
    func get<T>(urlString: String, modelCreator: @escaping (Data?) -> T?, completionHandler: @escaping (T?) -> Void) {
        guard let url: URL = URL(string: urlString) else {
            return
        }
        let request = URLRequest(url: url)
        
        let session = URLSession.shared
        let task = session.dataTask(with: request) { (data, response, error) in
            guard error == nil else {
                completionHandler(nil)
                return
            }
            
            guard let model = modelCreator(data) else {
                completionHandler(nil)
                return
            }
            completionHandler(model)
        }
        task.resume()
        return
    }
}

class Call<T>: NSObject, CommonCall {
    private var cancelled: Bool
    private var executed: Bool
    private let webClient: WebClient
    private let url: String
    private let modelCreator: (Data?) -> T?
    
    init(_webClient: WebClient, _url: String, _modelCreator: @escaping (Data?) -> T?) {
        self.webClient = _webClient
        self.url = _url
        self.modelCreator = _modelCreator
        cancelled = false
        executed = false
        
    }

    func enqueue(callback: CommonCallback) {
        if (executed || cancelled) {
            return
        }
        webClient.get(urlString: url, modelCreator: modelCreator) { (ipModel) in
            guard let model = ipModel else {
                // TODO create real error message
                let throwable = CommonStdlibThrowable(message: "An unknown error occured")
                OperationQueue.main.addOperation {
                    callback.onFailure(call: self, t: throwable)
                }
                return
            }
            let response = CommonResponse(_body: model, _errorBody: nil, _isSuccessful: true)
            OperationQueue.main.addOperation {
                callback.onResponse(call: self, response: response)
            }
            self.executed = true
        }
    }
    
    func isExecuted() -> Bool {
        return executed
    }
    
    func cancel() {
        cancelled = true
    }
    
    func isCanceled() -> Bool {
        return cancelled
    }
}

