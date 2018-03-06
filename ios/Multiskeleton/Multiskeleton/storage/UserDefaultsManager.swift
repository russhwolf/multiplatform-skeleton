//
// Created by Russell Wolf on 12/2/17.
// Copyright (c) 2017 Intrepid. All rights reserved.
//

import Foundation
import common

class UserDefaultsManager : NSObject, CommonUserSettings {
    private let KEY_IP = "ip"

    func getLastIp() -> String {
        return UserDefaults.standard.string(forKey: KEY_IP) ?? ""
    }

    func setLastIp(ip: String) {
        UserDefaults.standard.set(ip, forKey: KEY_IP)
    }
}
