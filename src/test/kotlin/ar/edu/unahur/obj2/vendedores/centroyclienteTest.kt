package ar.edu.unahur.obj2.vendedores

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe


class CentroClienteTest : DescribeSpec({
    val certificacion1 = Certificacion(esDeProducto = true, puntaje = 40)
    val certificacion2 = Certificacion(esDeProducto = false, puntaje = 30)
    val certificacion3 = Certificacion(esDeProducto = true, puntaje = 35)
    val certificacion4 = Certificacion(esDeProducto = true, puntaje =25)
    val buenosAires = Provincia(11000000)
    val sanClemente = Ciudad(buenosAires)
    val lasToninas = Ciudad(buenosAires)
    val centro = CentroDistribucion(sanClemente)
    val vendedor1 = Vendedor.VendedorFijo(sanClemente)
    val vendedor2 = Vendedor.VendedorFijo(sanClemente)
    val vendedor3 = Vendedor.VendedorFijo(lasToninas)
    val vendedor4 = Vendedor.VendedorFijo(sanClemente)
    centro.agregarVendedor(vendedor1)
    centro.agregarVendedor(vendedor2)
    centro.agregarVendedor(vendedor3)
    centro.agregarVendedor(vendedor4)
    vendedor1.agregarCertificacion(certificacion1)
    vendedor2.agregarCertificacion(certificacion2)
    vendedor3.agregarCertificacion(certificacion3)
    vendedor4.agregarCertificacion(certificacion1)
    vendedor4.agregarCertificacion(certificacion2)
    vendedor4.agregarCertificacion(certificacion3)
    vendedor4.agregarCertificacion(certificacion4)
    val comercio = Vendedor.ComercioCorresponsal(listOf(sanClemente))
    val inseguro = Inseguro()
    val detallista = Detallista
    val humanista = Humanista
    val cliente1 = inseguro
    val cliente2 = detallista
    val cliente3 = humanista


    //it("es que mas puntos por certificaciones tiene") {
    //centro.esVendedorEstrella().shouldContain

    //}

    describe("Puede cubrir"){
        it("si al menos 1 de los vendedores puede cubrir la ciudad dada"){
            centro.puedeCubrir(lasToninas).shouldBeTrue()
        }
    }

    describe("Vendedores genericos") {
        it("Tiene al menos una certificacion que no es de producto") {
            centro.puedeSerGenerico().shouldContain(vendedor2)
        }
    }


    describe("Es robusto") {
        it("Al menos tres de sus vendedores sea firme") {
            centro.esRobusto().shouldBeTrue()
        }
    }
    describe("cliente inseguro") {
        it("El vendedor debe ser versatil y firme") {
            cliente1.puedeAtenderlo(vendedor4).shouldBeTrue()
        }
    }
    describe("Es cliente detallista ") {
        it("El vendedor debe tener al menos 3 certifiaciones sobre productos") {
            cliente2.puedeAtenderlo(vendedor4).shouldBeTrue()
        }
    }
    describe("Es Humanista ") {
        it("El vendedor tiene que ser fisico") {
            cliente3.puedeAtenderlo(vendedor1).shouldBeTrue()
        }
    }
    describe(" Humanista ") {
        it("El vendedor debe ser  fisico") {
            cliente2.puedeAtenderlo(comercio).shouldBeFalse()
        }
    }


})