import java.util.*
import kotlin.collections.ArrayList

fun main(){
    println("Hola Mundo")
    // Inmutables (No se puede RE ASIGNAR "=")

    val inmutable: String = "Adrian";
    //inmutable = "Vicente" //Error (No vale porque es solo de lectura)


    //MUTABLES
    var mutable: String = "Vicente"
    mutable = "Vicente" //Si vale

    //VAL > VAR


    //DUCK TYPING
    var ejemploVariable = "Nardy"
    val edadEjemplo = 12
    ejemploVariable.trim()
    //ejemploVariable = edadEjemplo //Error, tipo incorrecto


    //VARIABLES PRIMITIVAS
    val nombre = "Adrian"
    val sueldo = 1.2
    val estadoCivil = 'C'
    val mayorEdad = true


    //CLASES EN JAVA
    val fechaNacimiento: Date = Date()


    //When(switch)
    val estadoCivilWhen = "C"
    when (estadoCivilWhen){
        ("C") ->{
            println("Casado")
        }
        "S" ->{
            println("Soltero")
        }
        else ->{
            println("K")
        }
    }
    val esSoltero = (estadoCivilWhen == "S")
    val coqueteo =  if (esSoltero) "Si" else "No" //if else pequeño


    calcularSueldo(10.00)
    calcularSueldo(10.00,15.00,20.00)
    //Named Parameters
    //CalcularSueldo(sueldo,tasa,bonoEspecial)
    calcularSueldo(10.00, bonoEspecial = 20.00)
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00)

    // Uso de clases
    val sumaUno = Suma(1,1) // new Suma(1,1) en KOTLIN no hay "new"
    val sumaDos = Suma(null,1)
    val sumaTres = Suma(1,null)
    val sumaCuatro = Suma(null,null)
    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)

    // Arreglos
    // Estaticos
    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println(arregloEstatico)
    // Dinamicos
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(
        1,2,3,4,5,6,7,8,9,10
    )
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    // FOR EACH = > Unit
    // Iterar un arreglo
    val respuestaForEach: Unit = arregloDinamico
        .forEach{ valorActual: Int ->
            println("Valor actual: ${valorActual}");
        }
    // "it" (en ingles "eso") significa el elemento iterado
    arregloDinamico.forEach{ println("Valor Actual (it): ${it}")}



}// Termina funcion Main


//void ->Unit

fun imprimirNombre(nombre:String): Unit{
    println("Nombre: ${nombre}")//Templete Strings
}

fun calcularSueldo(
    sueldo: Double, //Requerido
    tasa: Double = 12.00, ///Opcional (defecto)
    bonoEspecial: Double? = null  //Opcional (nullable)
):Double{
    //Int -> Int? (nullable)
    //Sting -> Sting? (nullable)
    //Date -> Date? (nullable)
    if(bonoEspecial ==null){
        return sueldo * (100/tasa)
    }else{
        return sueldo * (100/tasa) * bonoEspecial
    }
}

abstract class NumerosJava{
    protected val numeroUno:Int
    private val numeroDos: Int
    constructor(
        uno:Int,
        dos:Int
    ){
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }
}

abstract class Numeros(
    // Constructor Primar1o
// Caso 1) Parametro normal
// uno:Int , (parametro (sin modificador acceso))

// Caso 2) Parametro y propiedad (atributo) (private)
// private var uno: Int (propiedad "instancia.uno")


    protected val numeroUno: Int, // instancia.numeroUno
    protected val numeroDos: Int, // instancia.numeroDos
    //parametroInutil:String , //Parametro
) {
    init { // bloque constructor primario (OPCIONAL)
        this.numeroUno
        this.numeroDos
        //this.parametroInutil // EEROR NO EXISTE
        println("Inicializando")
    }
}

class Suma(
    // Constructor primario
    unoParametro: Int, // Parametro
    dosParametro: Int, // Parametros
) : Numeros( // Clase papa, Numeros (extendiendo)
    unoParametro,
    dosParametro
) {
    public val soyPublicoExplicito: String = "Explicito" // Publicas
    val soyPublicoImplicito: String = "Implicito" // Publicas (propiedades, metodos)

    init { // Bloque Codigo Constructor primario
        this.numeroUno

        this.numeroDos

        numeroUno // this. OPCIONAL (propiedades, metodos)
        numeroDos // this. OPCIONAL (propiedades, metodos)
        this.soyPublicoExplicito
        soyPublicoImplicito // this. OPCIONAL (propiedades, metodos)
    }
    constructor( // Constructor secundario
        uno:Int?,
        dos:Int
    ):this(
        if(uno== null) 0 else uno,
        dos
    )
    constructor( // Constructor tercero
        uno:Int,
        dos:Int?
    ):this(
        uno,
        if(dos== null) 0 else dos,
    )

    constructor( // Constructor cuarto
        uno:Int?,
        dos:Int?
    ):this(
        if(uno== null) 0 else uno,
        if(dos== null) 0 else dos,
    )
    // public fun sumar()Int
    fun sumar():Int{
        val total = numeroUno + numeroDos
        // SUma.agregarHistorial(total)
        // ("Suma." a "NombreClase." es OPCIONAL)
        agregarHistorial(total)
        return total
    }
    companion object { // Comparte entre todas
        // funciones y variables
        val pi = 3.14
        fun elevarAlCuadrado(num:Int):Int{
            return num * num
        }
        val historialSumas = arrayListOf<Int>()}
    fun agregarHistorial(valorTotalSuma:Int){
        historialSumas.add(valorTotalSuma)
    }
}

