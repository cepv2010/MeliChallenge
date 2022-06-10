# Meli Challenge

Desarrollo del challenge en al etapa de la fase tecnica, en donde se requiere el desarrollo du una app que contenga las siguientes condiciones:

- Campo de busqueda.
- Visualización de resultados de la búsqueda.
- Detalle de un producto.

**La app esta desarrollada con las siguientes dependencias:**
**min SDK:** 28
**Lenguaje:** Kotlin 1.6.21
**Base de datos:** Room


El app esta configurada para que se construya sin problemas. Tiene configurado Firebase, por lo tanto el archivo `google-services.json` se encuentra precargado, si desea cambiarlo, es reemplazarlo en el directorio `app`.

Si desea instalar por apk, los links son los siguientes:
- [Firebase](https://appdistribution.firebase.dev/i/76649a82af4b317a): Para descargarla hay que registrase como tester y debera serme notifcado para habilitar el acceso
- [testapp.io](https://portal.testapp.io/apps/install/d348kKLJvrkrZ): Su descarga es libre y esta abierta al publico no hay necesidad de registrarse

**Que contiene:**
- Unit Test
- Navigation Component
- Room Database
- Dagger Hilt (Dependency Injection)
- Firebase


**Que dependencias se usaron**
- Retrofit: Comunicación por API Rest
- Picasso: Lectura y cargue de imagenes por ImageView/Uri
- Firebase: Registro y control de logs
- Dagger Hilt: Inyección de dependencias
- Mockk: Uni test en Kotlin
- Androidx: Librerias estandar de desarrollo nativo de android para el uso de Android Jetpack
- LifeCycle: las dependencias para la adminsitración de datos temporales, gestión y cumplimiento del patrón MVVM(Model View View Model).


**Que estructura tiene:**
De acuerdo con el uso de Android Jetpack se utilizo dos tipos de patron estructural y de diseño:

- MVVM: Model View View Model
- Clean Architecture

Con las condiciones dadas anteriormente la estructura queda de la siguiente forma:

```
├───main
│   ├───java
│   │   └───com
│   │       └───camiloparra
│   │           └───melichallenge
│   │               ├───config //Configuración del app
│   │               │   └───di
│   │               │
│   │               ├───data 
│   │               │   │
│   │               │   ├───comm //Comunicación por API REST
│   │               │   │   └───Api
│   │               │   └───repository //Comunicación con la Base de Datos
│   │               │   
│   │               ├───domain
│   │               │   │
│   │               │   ├───dto //Todos los objetos utilizados dentro de la App
│   │               │   │   └───api
│   │               │   ├───entity //Modelo de datos o entidades representativas de la base de datos
│   │               │   └───useCase //Casos de uso o logica de negocio
│   │               │
│   │               ├───ui //Vistas, Activities, ViewModel
│   │               │   │
│   │               │   ├───itemDetail
│   │               │   ├───itemSearch
│   │               │   ├───main
│   │               │   └───shared
│   │               │       └───searchBar
│   │               │
│   │               └───util //Utilidades varias
│   └───res //Layouts, drawables, UI base
│
└───test //Pruebas unitarias y de integración
    └───java
        └───com
            └───camiloparra
                └───melichallenge
                    ├───domain
                    │   └───useCase
                    │
                    └───ui
                        ├───itemSearch
                        └───shared
                            └───searchBar
```