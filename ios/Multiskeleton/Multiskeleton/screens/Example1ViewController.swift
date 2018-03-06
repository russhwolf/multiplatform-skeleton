//
//  Example1ViewController.swift
//  Multiskeleton
//
//  Created by Russell Wolf on 2/19/18.
//  Copyright © 2018 Intrepid. All rights reserved.
//

import UIKit
import common


class Example1ViewController: UIViewController, CommonExample1ContractView {
    
    var presenter : CommonExample1ContractPresenter?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        guard let appDelegate = UIApplication.shared.delegate as? AppDelegate else {
            return
        }
        presenter = CommonExample1Presenter(view: self, configuration: appDelegate.getPresenterConfiguration())
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func gotoExample2() {
        let viewController = Example2ViewController(nibName: nil, bundle: nil)
        navigationController?.pushViewController(viewController, animated: true)
    }
    
    @IBAction func didClickButton() {
        presenter?.onButtonClick()
    }
}
