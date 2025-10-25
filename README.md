# 📱 Actividad 9 - App base de datos empleados
## 📋 Descripción

Aplicación Android que permite registrar, mostrar y eliminar empleados almacenados en una base de datos local. Incluye una interfaz con RecyclerView y un FloatingActionButton para manejar operaciones CRUD de manera sencilla.

## 🎯 Objetivos de aprendizaje

- ✅ Implementar operaciones CRUD en Android

- ✅ Integrar RecyclerView con base de datos

- ✅ Aplicar buenas prácticas en el uso de SQLite

- ✅ Manejar eventos en interfaz mediante FAB

- ✅ Mejorar la experiencia del usuario con feedback visual

## 🛠️ Tecnologías utilizadas

Lenguaje: Java

Base de datos: SQLite

UI: RecyclerView, FloatingActionButton, Material Design

Entorno: Android Studio (API 28+)

## 📱 Funcionalidades

- Registro de nuevos empleados

- Visualización de lista con RecyclerView

- Eliminación individual mediante FAB en cada item

-  Persistencia local con SQLite

-  Interfaz limpia y responsiva

### 🎨 Diseño

 - Paleta sobria y profesional

 - Uso de Material Design Components

 - Distribución adaptable con ConstraintLayout

 - FAB integrado en cada card de empleado para eliminar registros

## ❓ Preguntas de reflexión técnica:
1. **¿Qué características de SQL estándar no están disponibles en SQLite o SQL Server Compact?**

   Respuesta = No tiene muchas funciones de SQL ya que es una versión pequeña por lo tanto no tiene funciones avanzadas de permisos y joins, solo tiene lo básico para crear bases de datos.
2. **¿En qué casos sería SQLite la mejor opción para una aplicación móvil?**

   Respuesta = En aplicaciones que no necesitan mucho almacenamiento de datos como para necesitar una base externa, pero necesitan un almacenamiento moderado el cual requiera tener una base de datos sin conexión a internet.
3. **¿Qué prácticas recomendadas se deben seguir al usar SQLite en aplicaciones Android?**
   
   Respuesta = Siempre se recomienda cerrar las bases de datos con db.close para que no exista un error como también las mismas prácticas que se usan SQL normal como llevar un buen uso de las llaves primarias y separar los datos de buena manera para que las bases de datos no tengan que ser actualizadas tan profundo ya que pueden provocar fallos mayores o borrar datos.
### 💭 Reflexión personal:
Para poder almacenar datos que use la misma aplicación y que estas no requieran que la misma aplicación necesite guardarlas se usan las bases de datos que es de donde la aplicación podrá consultar esa información de ser necesario, pero para aplicaciones que no necesiten una base de datos tan grande (Como una agenda) se tienen alternativas gratuitas como SQLlite que es perfecto para aplicaciones móviles 
