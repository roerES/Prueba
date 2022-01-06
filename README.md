**Problemática a resolver:**
El problema consiste en desarrollar un servicio rest con por lo menos dos métodos,
el primero de ellos acumulará números y el segundo recibirá un operador, ejecutará
la operación sobre los términos acumulados hasta el momento de forma iterativa
retornando el valor calculado. El endpoint tiene que estar pensado para ser 
concurrente.

**REQUERIMIENTOS**:

**Versión de Java:** 11
**IDE:** Intellij IDEA

Los dos servicios desarrollados se encuentran en el directorio raíz,
es decir, http://localhost:8080/.

**Método para agregar valores:**

- Método POST _/addValue_ el cual recibe el parámetro _value_

 Endpoint: localhost:8080/?value={valor a agregar}

**Método para calcular resultado de operación:**

- Método GET _/calculateResult_ el cual recibe el parámetro _operator_ que permitirá
  realizar las diferentes operaciones que se especifican a continuación:
  _s_ -> Suma
  _-_ -> Resta
  _*_ -> Multiplicación
  _/_ -> División  

 Endpoint: localhost:8080/?operator={operador especificado}