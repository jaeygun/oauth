plugins {
    java
    id("org.springframework.boot") version "2.7.8"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
}

group = "com.jaeygun"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter-web-services")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.3.0")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("com.mysql:mysql-connector-j")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // JSP를 페이지로 인식하기 위한 라이브러리
    implementation ("org.apache.tomcat.embed:tomcat-embed-jasper")
    // JSTL을 사용할 경우 추가해야하는 라이브러리
    implementation ("javax.servlet:jstl:1.2")
    // Jquery
    implementation ("org.webjars:jquery:3.6.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
