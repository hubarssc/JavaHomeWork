package homework12

import java.util.*


fun main() {
    val stock = Stock();
    val broker = Broker("Иван Иваныч", stock);

    val anotherObserver = AnotherObserver("Петрович", stock)
    broker.addAnotherObserver(anotherObserver)

    // имитация торгов

    stock.market();

    // брокер прекращает наблюдать за торгами
    broker.stopTrade();

    // имитация торгов
    stock.market();

    broker.startTrade()

    stock.market();

}


interface IObserver {
    fun update(ob: Any): Unit
}

interface IObservable {
    fun registerObserver(o: IObserver)
    fun removeObserver(o: IObserver)
    fun notifyObservers()
}

class Stock : IObservable {
    private var sInfo: StockInfo = StockInfo() // информация о торгах
    private var observers = mutableListOf<IObserver>()

    public override fun registerObserver(o: IObserver) {
        observers.add(o)
    }

    public override fun removeObserver(o: IObserver) {
        observers.remove(o)
    }

    public override fun notifyObservers() {
        for (o in observers) {
            o.update(sInfo)
        }
    }

    public fun market() {
        sInfo.USD = 20 + Random().nextInt(20)
        sInfo.Euro = 30 + Random().nextInt(20)
        println("Market ${sInfo.USD}, ${sInfo.Euro}")
        notifyObservers()
    }
}

class StockInfo {
    var USD: Int = 0
    var Euro: Int = 0
}

class Broker(name: String, obs: IObservable) : IObserver {
    private var Name: String
    private var stock: IObservable
    private var anotherObserver: AnotherObserver? = null

    init {
        stock = obs
        Name = name
        stock.registerObserver(this)
    }

    fun addAnotherObserver(observer: AnotherObserver) {
        this.anotherObserver = observer
    }

    public override fun update(ob: Any) {
        val sInfo = ob as (StockInfo);

        if (sInfo.USD > 30)
            println("Брокер ${this.Name} продает доллары;  Курс доллара: ${sInfo.USD}")
        else
            println("Брокер ${this.Name} покупает доллары;  Курс доллара: ${sInfo.USD}")
    }

    public fun stopTrade() {
        stock.removeObserver(this)
    }

    fun startTrade() {
        stock.registerObserver(this)
    }
}

    class AnotherObserver(name: String, obs: IObservable) : IObserver {
        private var Name: String
        private var stock: IObservable

        init {
            stock = obs
            Name = name
            stock.registerObserver(this)
        }

        public override fun update(ob: Any) {
            val sInfo = ob as (StockInfo);

            if (sInfo.Euro > 35)
                println("Брокер ${this.Name} продает евро;  Курс евро: ${sInfo.Euro}")
            else
                println("Брокер ${this.Name} покупает евро;  Курс евро: ${sInfo.Euro}")
        }

        public fun stopTrade() {
            stock.removeObserver(this)
        }

        fun startTrade() {
            stock.registerObserver(this)
        }
    }

