package ar.edu.unahur.obj2.vendedores

class Certificacion(val esDeProducto: Boolean, val puntaje: Int)

abstract class Vendedor {
  // Acá es obligatorio poner el tipo de la lista, porque como está vacía no lo puede inferir.
  // Además, a una MutableList se le pueden agregar elementos
  val certificaciones = mutableListOf<Certificacion>()

  // Definimos el método abstracto.
  // Como no vamos a implementarlo acá, es necesario explicitar qué devuelve.
  abstract fun puedeTrabajarEn(ciudad: Ciudad): Boolean
  abstract fun esInfluyente(): Boolean

  // En las funciones declaradas con = no es necesario explicitar el tipo
  fun esVersatil() =
    certificaciones.size >= 3
            && this.certificacionesDeProducto() >= 1
            && this.otrasCertificaciones() >= 1

  // Si el tipo no está declarado y la función no devuelve nada, se asume Unit (es decir, vacío)
  fun agregarCertificacion(certificacion: Certificacion) {
    certificaciones.add(certificacion)
  }

  fun puedeSerGenerico() = this.otrasCertificaciones() >= 1
  fun esFirme() = this.puntajeCertificaciones() >= 30

  fun certificacionesDeProducto() = certificaciones.count { it.esDeProducto }
  fun otrasCertificaciones() = certificaciones.count { !it.esDeProducto }

  fun puntajeCertificaciones() = certificaciones.sumBy { c -> c.puntaje }

  abstract fun tieneAfinidad(centro: CentroDistribucion): Boolean

  abstract fun esFisico(): Boolean

  fun esCandidato(centro: CentroDistribucion) : Boolean {
    return this.esVersatil() && this.tieneAfinidad(centro)
  }

  // En los parámetros, es obligatorio poner el tipo
  class VendedorFijo(val ciudadOrigen: Ciudad) : Vendedor() {
    override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {
      return ciudad == ciudadOrigen
    }

    override fun esInfluyente(): Boolean {
      return false
    }


    override fun tieneAfinidad(centro: CentroDistribucion): Boolean {

      return this.puedeTrabajarEn(centro.ciudad)
    }

    override fun esFisico() = true


  }

    // A este tipo de List no se le pueden agregar elementos una vez definida
    class Viajante(val provinciasHabilitadas: List<Provincia>) : Vendedor() {
      override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {
        return provinciasHabilitadas.contains(ciudad.provincia)
      }

      fun poblacionTotal() =
        provinciasHabilitadas.sumBy { c -> c.poblacion }

      override fun esInfluyente() =
        this.poblacionTotal() >= 10000000

      override fun tieneAfinidad(centro: CentroDistribucion): Boolean {
        return this.puedeTrabajarEn(centro.ciudad)
      }

      override fun esFisico() = true
    }

    class ComercioCorresponsal(val ciudades: List<Ciudad>) : Vendedor() {
      override fun puedeTrabajarEn(ciudad: Ciudad): Boolean {
        return ciudades.contains(ciudad)
      }

      fun ciudadesNecesarias() =
        ciudades.size >= 5

      fun cantProvincias() =
        ciudades.map { c -> c.provincia }

      override fun esInfluyente() =
        this.ciudadesNecesarias() || this.cantProvincias().size >= 3

      fun lePuedeCubrirCiudad(centro: CentroDistribucion) = ciudades.any { c -> centro.puedeCubrir(c).not() }

      override fun tieneAfinidad(centro: CentroDistribucion): Boolean {
        return this.puedeTrabajarEn(centro.ciudad)
                && this.lePuedeCubrirCiudad(centro)
      }

      override fun esFisico() = false

    }
  }





