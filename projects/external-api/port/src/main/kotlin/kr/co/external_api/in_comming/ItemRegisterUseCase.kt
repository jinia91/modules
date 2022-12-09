package kr.co.external_api.in_comming

interface ItemRegisterUseCase {
    fun register(command : RegisterCommand): Long
}