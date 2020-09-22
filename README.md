# nxloghomework
 * Implement getModulesIncludedInCompleteRoutes method, it:
 * Returns all modules for agent which are included in a complete route.
 * A complete route contains at least 1 INPUT module and at least 1 OUTPUT module.
 
# to run app
mvn spring-boot:run

# expected result
Module(agent=Agent{name='Agent #1'}, name=Module #1, type=INPUT)
Module(agent=Agent{name='Agent #1'}, name=Module #2, type=OUTPUT)
Module(agent=Agent{name='Agent #1'}, name=Module #3, type=EXTENSION)
Module(agent=Agent{name='Agent #1'}, name=Module #6, type=PROCESSOR)
