package ar.edu.unahur.obj2.vendedores

abstract class Cliente {
    abstract fun puedeAtenderlo(vendedor: Vendedor) : Boolean
}


class Inseguro : Cliente()  {
     override fun puedeAtenderlo(vendedor: Vendedor) = vendedor.esVersatil() && vendedor.esFirme()
    }

object Detallista : Cliente()  {
     override fun puedeAtenderlo(vendedor: Vendedor) = vendedor.certificacionesDeProducto() >= 3
    }

object Humanista : Cliente(){
     override fun puedeAtenderlo(vendedor: Vendedor) = vendedor.esFisico()

    }
