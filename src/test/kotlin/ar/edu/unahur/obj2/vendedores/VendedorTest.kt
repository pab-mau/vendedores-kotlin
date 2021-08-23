package ar.edu.unahur.obj2.vendedores

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue


class VendedorTest : DescribeSpec({

  val buenosAires = Provincia(11000000)
  val misiones = Provincia(1300000)
  val sanIgnacio = Ciudad(misiones)
  val certificacion1 = Certificacion(esDeProducto = true, puntaje = 20)
  val certificacion2 = Certificacion(esDeProducto = false, puntaje = 5)
  val certificacion3 = Certificacion(esDeProducto = true, puntaje = 15)
  val obera = Ciudad(misiones)
  val vendedorFijo = Vendedor.VendedorFijo(obera)
  val centro1 = CentroDistribucion(ciudad = obera)
  val cordoba = Provincia(2000000)
  val villaDolores = Ciudad(cordoba)
  val viajante = Vendedor.Viajante(listOf(misiones))
  val viajante2 = Vendedor.Viajante(listOf(buenosAires))
  val baradero = Ciudad(buenosAires)
  val tigre = Ciudad(buenosAires)
  val escobar = Ciudad(buenosAires)
  val zarate = Ciudad(buenosAires)
  val iguazu = Ciudad(misiones)
  val comercioCorresponsal = Vendedor.ComercioCorresponsal(listOf(obera, tigre, baradero, escobar, zarate))
  vendedorFijo.agregarCertificacion(certificacion1)
  vendedorFijo.agregarCertificacion(certificacion2)
  vendedorFijo.agregarCertificacion(certificacion3)

  describe("Vendedor fijo") {


    describe("puedeTrabajarEn") {

      it("su ciudad de origen") {
        vendedorFijo.puedeTrabajarEn(obera).shouldBeTrue()
      }
      it("otra ciudad") {
        vendedorFijo.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
      it("tiene tres certificaciones, al menos 1 que no sea sobre productos") {
        vendedorFijo.esVersatil().shouldBeTrue()
      }
      it("puntaje mayor a 30 puntos") {
        vendedorFijo.esFirme().shouldBeTrue()
      }
      it("no es influyente") {
        vendedorFijo.esInfluyente().shouldBeFalse()
      }
    }
  }
  describe("Viajante") {

    describe("puedeTrabajarEn") {
      it("una ciudad que pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(sanIgnacio).shouldBeTrue()
      }
      it("una ciudad que no pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(villaDolores).shouldBeFalse()
      }
      it("la poblacion total a las provincias deber ser de 10000000 o mas") {
        viajante2.esInfluyente().shouldBeTrue()
      }
    }
  }
  describe("Comercio Corresponsal") {


    describe("puedeTrabajarEn") {
      it("una ciudad donde tiene sucusal") {
        comercioCorresponsal.puedeTrabajarEn(tigre).shouldBeTrue()

      }
      it("una ciudad donde no tiene sucursal") {
        comercioCorresponsal.puedeTrabajarEn(iguazu).shouldBeFalse()
      }
      it("sucursales en almenos 5 ciudades o en al menos 3 provincias") {
        comercioCorresponsal.esInfluyente().shouldBeTrue()
      }
    }
    describe("Tiene afinidad") {
      it("Puede trabajar en la misma ciudad que esta el centro") {
        vendedorFijo.tieneAfinidad(centro1).shouldBeTrue()
      }
    }
    describe("no Tiene afinidad") {
      it("No puede trabajar en la misma ciudad que esta el centro") {
        viajante2.tieneAfinidad(centro1).shouldBeFalse()
      }
    }
    describe("Tiene afinidad comercio") {
      it("Puede trabajar en la misma ciudad que esta el centro y puede cubrir en la que no esta") {
          comercioCorresponsal.tieneAfinidad(centro1).shouldBeTrue()
      }
    }
    describe("Es candidato"){
      it("El vendedor debe ser versatil y debe tener afinidad por el centro"){
        vendedorFijo.esCandidato(centro1).shouldBeTrue()

      }
    }
  }
})
