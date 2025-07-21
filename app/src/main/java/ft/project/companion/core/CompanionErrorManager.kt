package ft.project.companion.core

import kotlinx.coroutines.flow.SharedFlow

interface CompanionErrorManager {

    val errorEventFlow: SharedFlow<ErrorEvent>
    /**
     * emits an event on a [kotlinx.coroutines.flow.SharedFlow] with the object ErrorEvent(msg = "Error: " + msg)
     */
    suspend fun emitError(msg: String)
}