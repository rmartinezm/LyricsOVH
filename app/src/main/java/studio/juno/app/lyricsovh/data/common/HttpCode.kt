package studio.juno.app.lyricsovh.data.common

/**
 *
 *
 */
enum class HttpCode {

    OK,
    NOT_FOUND,
    UNKNOWN;

    companion object {

        /**
         *
         *
         */
        fun get(code: Int? = null) : HttpCode = when (code) {
            200 -> OK
            404 -> NOT_FOUND
            else -> UNKNOWN
        }

    }


}