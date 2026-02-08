# *GUIAME-APP*

# *GoogleDoc:* https://docs.google.com/document/d/1Tu067E5pNAi8JbRcgCUH_MvGvrlp6kN8T5bRuWEwOqE/edit?usp=sharing

*Objective & Scope*
  Problemática: El cáncer de mama es un padecimiento que en su mayoría lo sufren las mujeres, por eso es importante detectarlo e intervenir a tiempo, antes de que se vuelva irremediable.

  Solución: Crear una App para informar acerca del cáncer de mama, donde tenga información relacionada sobre el, cómo qué es, sus síntomas y que implica una detección temprana mediante el autoexamen.

  Audiencia Objetivo: Mujeres adolescentes y adultas, posibles pacientes y pacientes de cáncer de mama.

  Beneficio: Concientizar a las usuarias sobre el cáncer de mama, cómo realizar un autoexamen, monitoreo de los síntomas y generación de reportes acerca de ellos, 
  así mismo acompañamiento antes, durante y después de la detección del cáncer de mama.

*Key Features*
  -*Authentication system and Data Storage*
    - Firebase Authentication: Para registrar a las usuarias de la aplicación de manera ordenada y segura.
    - Cloud Firestore: Como base de datos en tiempo real para que solo la usuaria pueda ver los registros de sus síntomas en la página web.
      
  -*5 Additional Features*
    1. Registro de autoexámenes: Un log histórico donde la usuaria marque fechas y hallazgos.
    2. Monitoreo de síntomas evolutivos: Un diario donde se registran cambios físicos a lo largo del tiempo.
    3. Información educativo interactiva: Información sobre lo que es normal y qué aspectos podrían ser una alerta.
    4. Recordatorio de autoexamen: Sistema de notificaciones que recuerde realizar el autoexamen al menos 1 vez al mes.
    5. Reportes generados por IA: Reporte generado con IA en base a los datos del log para determinar si es necesaria una cita médica así como facilitar la comunicación con el médico.

*Technological justifications*
  - Next.js: Para permitir que familiares o pacientes vean su historial en pantalla grande.
  - Kotlin: Para aprovechar sensores del móvil y notificaciones nativas que son críticas en salud.
  - Firebase Functions: Como puente (backend) para procesar los datos antes de enviarlos a la inteligencia artificial.

*Initial Sketch*

