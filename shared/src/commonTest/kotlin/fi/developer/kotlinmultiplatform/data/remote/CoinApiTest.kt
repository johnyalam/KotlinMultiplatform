package fi.developer.kotlinmultiplatform.data.remote

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CoinApiTest {

    @Test
    fun getCoins_parses_successful_response_into_CoinItem_list() = runTest {
        val mockEngine = MockEngine { request ->
            respond(
                // FIXED: Wrapped rank value in quotation marks "1" to match your String type definition
                content = """[{"id":"btc-bitcoin","name":"Bitcoin","symbol":"BTC","rank":"1","is_active":true,"type":"coin"}]""",
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }
        val client = HttpClient(mockEngine) {
            expectSuccess = true // Tells Ktor to throw on bad status codes
            install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        }
        val api = CoinApi(client)

        val result = api.getCoins()

        assertEquals(1, result.size)
        assertEquals("Bitcoin", result[0].name)
    }

    @Test
    fun getCoins_throws_on_server_error() = runTest {
        val mockEngine = MockEngine { request ->
            respond(
                content = "",
                status = HttpStatusCode.InternalServerError
            )
        }
        val client = HttpClient(mockEngine) {
            expectSuccess = true // FIXED: Forces Ktor to throw ResponseException / ClientRequestException on 500 error
        }
        val api = CoinApi(client)

        // Now Ktor correctly throws a ServerResponseException/ResponseException on 500 status
        assertFailsWith<ResponseException> {
            api.getCoins()
        }
    }
}