package com.svenss.realmcoroutines.model

import io.realm.RealmObject

/**
 * Created by miguelleon on 25,enero,2022
 */

open class ProfileRealm (
    var name: String? = "",
    var lastname: String? = ""
): RealmObject()


data class ProfileWithoutRealm (
    var name: String? = "",
    var lastname: String? = ""
)