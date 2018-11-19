# Installation

Ce projet est basé sur une architecture Maven. Pour installer l'outil, il suffit d'intaller les dépendances Maven.
Pour cela, selon votre environnment, voici les étapes à suivrent : 
- IntelliJ (en anglais selon la documentation officielle) :
    - On the main menu, select File | Open.
    - In the dialog that opens, select the pom.xml of the project you want to import. Click OK.
    - On the first page of the Import Project wizard, in the Import Project from External model select Maven and click Next. 
    (This page is not displayed if you selected the pom.xml.)
    - Specify Maven settings or use the default selection.
    The default settings are usually sufficient for a project. However, you can select the following (frequently used) options:
        - Search for projects recursively - if you select this option, the sub projects (if any) are located and set up correctly.
        - Import Maven projects automatically - if you select this option, the project is imported automatically every time you make changes to your POM file and you don't need to control manually when to import the changes. However, note that it might take some time to re-import a large project. Also, note that the changes made in the IntelliJ IDEA project (for example, adding a dependency to your project through the Project Structure dialog) will be overwritten on re-import by POM since IntelliJ IDEA considers the POM file as a single source of truth.
    - Click Next.
    If IntelliJ IDEA detects profiles in your project, it displays them next.
    - IntelliJ IDEA displays the found projects and you can select the ones you need to import.
    Click Next.
    - Specify the project's SDK and click Next.
    - Specify a name and the location of your project. Click Finish.
    
- Eclipse (en anglais selon la documentation officielle) :
    - Open eclipse.
    - Click File > Import.
    - Type Maven in the search box under Select an import source:
    - Select Existing Maven Projects.
    - Click Next.
    - Click Browse and select the folder that is the root of the Maven project (probably contains the pom.xml file)
    - Click Next.
    - Click Finish.