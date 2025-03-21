
import com.App
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class TestBot {

    companion object {

        val originalIn = System.`in` // 표준 입력
        val originalOut = System.out // 표준 출력

        fun run(input: String): String {
            var formattedInput = input.trimIndent().plus("\n종료")

            val out = ByteArrayOutputStream()
            val testOut = PrintStream(out) // 커스텀 출력
            System.setIn(formattedInput.byteInputStream()) // 커스텀 입력 - 매개변수 문자열
            System.setOut(testOut)

            val app = App()
            app.run()

            // 표준 입출력으로 되돌리기
            System.setIn(originalIn)
            System.setOut(originalOut)

            return out.toString().trim().replace("\\r\\n", "\\n")
        }
    }
}
