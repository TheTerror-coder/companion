package ft.project.`companion`.presentation.utils.components.companionvectorspack

import androidx.compose.ui.graphics.vector.ImageVector
import ft.project.`companion`.presentation.utils.components.companionvectorspack.myiconpack.Fortytwo
import ft.project.`companion`.presentation.utils.components.companionvectorspack.myiconpack.Shield
import kotlin.collections.List as ____KtList

public object MyIconPack

private var __AllIcons: ____KtList<ImageVector>? = null

public val MyIconPack.AllIcons: ____KtList<ImageVector>
  get() {
    if (__AllIcons != null) {
      return __AllIcons!!
    }
    __AllIcons= listOf(Fortytwo, Shield)
    return __AllIcons!!
  }
