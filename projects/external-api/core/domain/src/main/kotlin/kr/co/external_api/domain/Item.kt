package kr.co.external_api.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Item private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0,
    var content : String
){
    companion object {
        fun newOne(content : String) = Item(content = content)
    }
}