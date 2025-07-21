package ft.project.companion.domain.result

sealed class Result<out T>{
    data class Success<T>(val value: T): Result<T>()
    object Error: Result<Nothing>()
}