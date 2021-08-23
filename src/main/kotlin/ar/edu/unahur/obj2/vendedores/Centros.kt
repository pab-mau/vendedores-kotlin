package ar.edu.unahur.obj2.vendedores

class CentroDistribucion(val ciudad: Ciudad){
    val vendedores= mutableListOf<Vendedor>()
    fun agregarVendedor(vendedor: Vendedor){
        vendedores.add(vendedor)
    }

    fun esVendedorEstrella() = vendedores.maxByOrNull { v -> v.puntajeCertificaciones() }

    fun puedeCubrir(ciudad: Ciudad) = vendedores.any{ v -> v.puedeTrabajarEn(ciudad)}

    fun puedeSerGenerico() = vendedores.filter { v -> v.puedeSerGenerico() }

    fun esRobusto() = vendedores.count { v -> v.esFirme() } >= 3

    fun repartir(certificacion: Certificacion){
        vendedores.forEach { v -> v.agregarCertificacion(certificacion) }
    }
}

