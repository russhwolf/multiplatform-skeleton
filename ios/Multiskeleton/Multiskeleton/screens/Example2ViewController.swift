//
//  Example2ViewController.swift
//  Multiskeleton
//
//  Created by Russell Wolf on 2/19/18.
//  Copyright © 2018 Intrepid. All rights reserved.
//

import UIKit
import common

class Example2ViewController: UIViewController, CommonExample2ContractView {

    var presenter: CommonExample2Presenter?

    @IBOutlet
    var currentText: UILabel?

    @IBOutlet
    var previousText: UILabel?

    override func viewDidLoad() {
        super.viewDidLoad()
        
        guard let appDelegate = UIApplication.shared.delegate as? AppDelegate else {
            return
        }
        presenter = CommonExample2Presenter(view: self, configuration: appDelegate.getPresenterConfiguration())
        presenter?.onViewCreated()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func showCurrentIpAddress(ip: String) {
        currentText?.text = "Your current Ip address is " + ip
    }
    
    func showPreviousIpAddress(ip: String) {
        previousText?.text = "Your previous Ip address is " + ip
        previousText?.isHidden = false
    }
    
    func hidePreviousIpAddress() {
        previousText?.isHidden = true
    }
    

}
