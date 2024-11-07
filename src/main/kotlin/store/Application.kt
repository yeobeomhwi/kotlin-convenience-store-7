package store

import java.io.File

fun main() {
    // TODO: 프로그램 구현
    productInventory();
}


fun productInventory() {
    println("안녕하세요. W편의점입니다.\n현재 보유하고 있는 상품입니다.");
    readProducts()
}

fun readProducts() {
    val file = File("src/main/resources/products.md")
    val productsList = mutableListOf<String>()

    file.readLines().drop(1).forEach { line ->
        val parts = line.split(",")

        // 분할된 요소가 네 개인지 확인하여 불완전한 데이터를 건너뛰기
        if (parts.size == 4) {
            val (name, price, quantity, promotion) = parts
            val formattedPrice = "%,d원".format(price.toInt())
            val formattedQuantity = if (quantity.toInt() > 0) "${quantity.toInt()}개" else "재고 없음"
            val productInfo = formatProductInfo(name, formattedPrice, formattedQuantity, promotion)
            productsList.add(productInfo)
        }
    }
    println()
    productsList.forEach { println(it) }
}


fun formatProductInfo(name: String, price: String, quantity: String, promotion: String): String {
    return if (promotion != "null") {
        "- $name $price $quantity $promotion"
    } else {
        "- $name $price $quantity"
    }
}