dependencies {
    implementation(project(":model"))
    implementation(project(":source"))
    implementation(project(":validator"))
    implementation(project(":queue"))

}

tasks.bootJar {
    enabled = true
}