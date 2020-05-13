package studio.juno.app.lyricsovh.presentation.common

import java.text.Normalizer
import java.util.*

/* */
val String.Companion.empty: String get() = ""

/* */
val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()


/**
 *
 */
fun String.normalize() = REGEX_UNACCENT.replace(
    Normalizer.normalize(this, Normalizer.Form.NFD), String.empty
).toLowerCase(Locale.US)