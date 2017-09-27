package cn.nbhope.smarthome.logtest

import java.io.Serializable

/**
 * Created by pengwei08 on 2015/7/20.
 */
class PersonK : Serializable {
    var name: String? = null
    var age: Int = 0
    var score: Float = 0.toFloat()
    private val other: PersonK? = null

}
