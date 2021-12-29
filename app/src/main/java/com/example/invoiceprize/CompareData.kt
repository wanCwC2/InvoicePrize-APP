package com.example.invoiceprize

import com.example.invoiceprize.prize.*

class CompareData {
    fun compare(index: Int, prize: Int): Array<String?> {
        val data: Array<Prize> = arrayOf<Prize>(
            SpecialPrize(),
            GrandPrize(),
            FirstPrize(),
            TwoPrize(),
            ThreePrize(),
            FourPrize(),
            FivePrize(),
            SixPrize(),
            AddsixPrize(),
            NotPrize()
        )
        var number = 0
        val enter = Enter()
        print("輸入你的完整發票號碼，共8碼：")
        while (!enter.vertified(8)) {
            println("輸入格式錯誤")
            print("輸入你的完整發票號碼，共8碼：：")
        }
        if (index == 0) {
            if (enter.integer === prize) {
                number = 0
                System.out.println(
                    "恭喜中" + data[number].name.toString() + " 得到獎金為" + data[number].bonus
                )
            } else {
                number = 9
                System.out.println(data[number].name)
            }
        } else if (index == 1) {
            if (enter.integer == prize) {
                number = 1
                System.out.println(
                    "恭喜中" + data[number].name.toString() + " 得到獎金為" + data[number].bonus
                )
            } else {
                number = 9
                System.out.println(data[number].name)
            }
        } else if (index == 2 || index == 3 || index == 4) {
            number = 2
            val counter = 100000000
            number = firstPrize(enter.integer, prize, counter, number)
            if (number != 9) {
                System.out.println(
                    "恭喜中" + data[number].name.toString() + " 得到獎金為" + data[number].bonus
                )
            } else {
                System.out.println(data[number].name)
            }
        } else if (index == 5 || index == 6) {
            if (enter.integer % 1000 === prize) {
                number = 8
                System.out.println(
                    "恭喜中" + data[number].name.toString() + " 得到獎金為" + data[number].bonus
                )
            } else {
                number = 9
                System.out.println(data[number].name)
            }
        }
        return arrayOf(
            number.toString(), enter.string,
            data[number].name, Integer.toString(data[number].bonus)
        )
    }

    fun firstPrize(enter: Int, prize: Int, counter: Int, number: Int): Int {
        return if (counter < 1000) {
            9
        } else {
            if (enter % counter == prize % counter) {
                number
            } else {
                firstPrize(enter, prize, counter / 10, number + 1)
            }
        }
    }
}