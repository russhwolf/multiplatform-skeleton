//
//  RestApi.swift
//
//
//  Created by Russell Wolf on 2/18/18.
//

import Foundation
import common

class RestClient: NSObject, CommonRestApi {
    private let webClient = WebClient()
    private let decoder = JSONDecoder()
    
    func getMyIp() -> CommonCall {
        let url = "https://api.ipify.org/?format=json"
        let modelCreator = { (data: Data?) -> CommonIpModel? in
            guard let responseData = data else {
                return nil
            }
            do {
                let ipModel = try self.decoder.decode(IpModel.self, from: responseData).toCommonModel()
                return ipModel
            } catch {
                return nil
            }
        }
        return Call(_webClient: webClient, _url: url, _modelCreator: modelCreator)
    }
}

private class IpModel: Codable {
    let ip: String
    
    init(_ip: String) {
        self.ip = _ip
    }
    
    func toCommonModel() -> CommonIpModel {
        let commonModel = CommonIpModel()
        commonModel.ip = self.ip
        return commonModel
    }
}

private let ERROR_DOMAIN = Bundle.main.bundleIdentifier!
private let ERROR_URL = 1
private let ERROR_SERIALIZATION = 2

private class WebClient {
    
    func get<T>(urlString: String, modelCreator: @escaping (Data?) -> T?, completionHandler: @escaping (T) -> Void, errorHandler: @escaping (Error) -> Void) {
        guard let url: URL = URL(string: urlString) else {
            errorHandler(NSError.init(domain: ERROR_DOMAIN, code: ERROR_URL, userInfo: ["url": urlString]))
            return
        }
        let request = URLRequest(url: url)
        let session = URLSession.shared
        let task = session.dataTask(with: request) { (data, response, error) in
            if error != nil {
                errorHandler(error!)
                return
            }
            
            guard let model = modelCreator(data) else {
                errorHandler(NSError.init(domain: ERROR_DOMAIN, code: ERROR_SERIALIZATION, userInfo: ["data": data ?? []]))
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
    
    fileprivate init(_webClient: WebClient, _url: String, _modelCreator: @escaping (Data?) -> T?) {
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
        webClient.get(urlString: url, modelCreator: modelCreator, completionHandler: { model in
            let response = CommonResponse(_body: model, _errorBody: nil, _isSuccessful: true)
            OperationQueue.main.addOperation {
                callback.onResponse(call: self, response: response)
            }
            self.executed = true
        }, errorHandler: { error in
            let throwable = CommonStdlibThrowable(message: error.localizedDescription)
            OperationQueue.main.addOperation {
                callback.onFailure(call: self, t: throwable)
            }
        })
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

