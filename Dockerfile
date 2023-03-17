FROM openjdk:17

COPY out/artifacts/ChatGPTDemo_jar/ChatGPTDemo.jar ChatGPTDemo.jar

ENTRYPOINT ["java", "-jar", "ChatGPTDemo.jar"]