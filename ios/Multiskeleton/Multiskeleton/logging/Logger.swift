//
//  Logger.swift
//  Multiskeleton
//
//  Created by Russell Wolf on 2/19/18.
//  Copyright © 2018 Intrepid. All rights reserved.
//

import Foundation
import common

class Logger: NSObject, CommonLogger {
    func v(message: String) {
        NSLog("v: " + message)
    }
    
    func v(t: CommonStdlibThrowable, message: String) {
        NSLog("v: " + message)
        t.printStackTrace()
    }
    
    func v(t: CommonStdlibThrowable) {
        NSLog("v: " + (t.message ?? ""))
        t.printStackTrace()
    }
    
    func d(message: String) {
        NSLog("d: " + message)
    }
    
    func d(t: CommonStdlibThrowable, message: String) {
        NSLog("d: " + message)
        t.printStackTrace()
    }
    
    func d(t: CommonStdlibThrowable) {
        NSLog("d: " + (t.message ?? ""))
        t.printStackTrace()
    }
    
    func i(message: String) {
        NSLog("i: " + message)
    }
    
    func i(t: CommonStdlibThrowable, message: String) {
        NSLog("i: " + message)
        t.printStackTrace()
    }
    
    func i(t: CommonStdlibThrowable) {
        NSLog("i: " + (t.message ?? ""))
        t.printStackTrace()
    }
    
    func w(message: String) {
        NSLog("w: " + message)
    }
    
    func w(t: CommonStdlibThrowable, message: String) {
        NSLog("w: " + message)
        t.printStackTrace()
    }
    
    func w(t: CommonStdlibThrowable) {
        NSLog("w: " + (t.message ?? ""))
        t.printStackTrace()
    }
    
    func e(message: String) {
        NSLog("e: " + message)
    }
    
    func e(t: CommonStdlibThrowable, message: String) {
        NSLog("e: " + message)
        t.printStackTrace()
    }
    
    func e(t: CommonStdlibThrowable) {
        NSLog("e: " + (t.message ?? ""))
        t.printStackTrace()
    }
    
    func wtf(message: String) {
        NSLog("wtf: " + message)
    }
    
    func wtf(t: CommonStdlibThrowable, message: String) {
        NSLog("wtf: " + message)
        t.printStackTrace()
    }
    
    func wtf(t: CommonStdlibThrowable) {
        NSLog("wtf: " + (t.message ?? ""))
        t.printStackTrace()
    }
}
