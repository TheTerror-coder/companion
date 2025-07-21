package ft.project.companion.core

import android.util.Log
import ft.project.companion.TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class CompanionErrorManagerImpl: CompanionErrorManager {

    private val _errorEventFlow = MutableSharedFlow<ErrorEvent>()

    override val errorEventFlow: SharedFlow<ErrorEvent>
        get() = _errorEventFlow.asSharedFlow()

    init {
        Log.i(
            TAG,
            "************** CompanionErrorManagerImpl instance Initialisation **************"
        )
    }

    override suspend fun emitError(msg: String){
        _errorEventFlow.emit(ErrorEvent(msg = "Error: $msg"))
    }
}