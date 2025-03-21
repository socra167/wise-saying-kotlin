
import com.App
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class TestBot {

    companion object {

        val originalIn = System.`in` // 표준 입력
        val originalOut = System.out // 표준 출력

        fun run(input: String): String {
            var formattedInput = input
                .trimIndent()
                .plus("\n종료")

            // Kotlin 에서 use 를 사용하면 Closeable 의 자동 해제를 할 수 있다
            // use 안에서 예외가 발생하거나 끝나면 자동으로 해제된다
            val out = ByteArrayOutputStream().use { out ->
                // Java 에서 리소스 해제하는 try 와 유사하다
                val testOut = PrintStream(out).use {
                    try {
                        System.setOut(it)
                        System.setIn(formattedInput.byteInputStream()) // 커스텀 입력 - 매개변수 문자열

                        val app = App()
                        app.run()
                    } finally {
                        // 표준 입출력으로 되돌리기
                        System.setIn(originalIn)
                        System.setOut(originalOut)
                    }
                }
                return out.toString().trim().replace("\\r\\n", "\\n")
            }
        }
    }
}
