# NEXTGENMMA - MANUAL DE USUARIO

## 🛠️ PASOS DE INSTALACIÓN

1. **Descargar el proyecto**

2. **Si vas a usar la versión de servidor**, por favor contacta con: [diegogilzg@gmail.com](mailto:diegogilzg@gmail.com) y pasa al último paso.

3. **Versión local**:
    - Configurar el archivo `.properties` del proyecto.
    - Ejecutar el archivo `.jar`.

✅ ¡Disfruta de la gestión de tu federación o gimnasio!

---

## 🧾 INSERCIÓN DE DATOS

### 🥋 PELEADOR

**Requisitos:**
- Nombre
- Apellido
- DNI
- Peso
- Victorias
- Apodo
- Entrenador
- Gimnasio
- Liga

**Campos:**
- Nombre y Apellido: máximo 250 caracteres (se recorta y muestra advertencia si se excede).
- DNI: máximo 15 caracteres.
- Peso: numérico (kg).
- Apodo: máximo 250 caracteres.
- Victorias: valor numérico.
- Debes seleccionar Gimnasio, Entrenador y Liga (si aplica).

---

### 🏋️ GIMNASIO

**Requisitos:**
- Nombre
- Contraseña
- CIF
- Dirección
- Web
- Ciudad

**Campos:**
- Nombre y Contraseña: máximo 250 caracteres (la contraseña está hasheada).
- CIF: sin letra.
- Dirección: debe ser la exacta.
- Web: debe ser la del club.
- Ciudad: ubicación del club.

---

### 🧑‍🏫 ENTRENADOR

**Requisitos:**
- Nombre
- Apellido
- DNI
- Experiencia
- Número de colegiado
- Gimnasio

**Campos:**
- Nombre y Apellido: máximo 250 caracteres.
- DNI: máximo 15 caracteres.
- Experiencia: años (valor entero).
- Nº Colegiado: debe coincidir con la cartilla.
- Gimnasio: debe seleccionarse.

---

### 🏆 LIGA

**Requisitos:**
- Nombre
- Ciudad
- Participantes
- Federación

**Campos:**
- Nombre: máximo 250 caracteres.
- Ciudad: ubicación de la promotora o federación.
- Participantes: cantidad estimada.
- Federación: debe seleccionarse.

---

### 🌐 FEDERACIÓN

**Requisitos:**
- Nombre
- Arte marcial
- Fecha de fundación
- Nº de asociación

**Campos:**
- Nombre: máximo 250 caracteres.
- Arte marcial: obligatoria.
- Fecha de fundación: seleccionable en calendario.
- Nº Asociación: debe coincidir con el registro oficial.

---

### 📢 POST

**Requisitos:**
- Título
- Mensaje
- Imagen (opcional)

**Campos:**
- Título: encabezado del post.
- Mensaje: contenido del post.
- Imagen: no debe tener gran resolución (opcional).

---

## 📩 ENVÍO DE CORREO

**Requisitos:**
- Asunto
- Nombre
- Apellido
- DNI
- Peso
- Victorias
- Apodo
- Fecha de nacimiento
- Gimnasio
- Entrenador
- Liga

**Campos:**
- Asunto: describe el propósito del mensaje.
- Nombre y Apellido: máximo 250 caracteres.
- DNI: máximo 15 caracteres.
- Peso y Victorias: valores numéricos.
- Apodo: máximo 250 caracteres.
- Debes seleccionar Gimnasio, Entrenador y Liga (si aplica).

---

## 📊 INFORMES

El administrador podrá ver los siguientes informes:

- Peleadores
- Gimnasios
- Entrenadores
- Ligas
- Peleadores por liga
- Peleadores por gimnasio

🧠 **Cada gimnasio podrá ver sus peleadores listados y contados.**

---

> 📌 Para más información o soporte, no dudes en contactar con el equipo de desarrollo de **NEXTGENMMA**.
