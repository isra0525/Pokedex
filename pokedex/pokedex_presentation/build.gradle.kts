apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.pokedexDomain))
    "implementation"(Coil.coilCompose)

}