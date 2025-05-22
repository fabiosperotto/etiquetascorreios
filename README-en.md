# Label Generator for Post Office

[README pt-BR](README.md) | [README english](README-en.md)

![Screenshot of the application showing the internal screens of the system](./image.png 'Application screenshot')

This is a learning project about desktop programming with Java, graphical interfaces in Java Swing, data persistence with Hibernate, Internationalization and Exporting data to PDF. The system should not be used for commercial or production purposes, serving only as an instructional reference in courses in the area.

## Installation for use

Download the latest version available. JDK 18+ is required to run the application. The project execution directory must have the .jar executable and the language files directory.

## Features

- People registration (currently only stores the name);
- Address registration, with street and number. Each person can have multiple addresses.
- Label generator: for each address it is possible to generate a recipient label, it opens the PDF automatically after each request to generate the file. There is room for improvement here as the label is partially ready.

## For Developers

The project uses Maven as a dependency manager, and the directory structure is as follows:

- DAO: classes that operate database aspects with entities;
- entities: system entity classes (MVC Model);
- gui: system graphical interface classes (MVC Controller/View);
- services: third-party system service classes, here currently resides the class that generates the PDF;
- utilities: utility classes for all other system components, may contain test data generators, database configurations, etc.

After downloading for development, in the project root, rename the hibernate-example.cfg.xml file to hibernate.cfg.xml and enter your database credentials. The current configuration will create the database based on the entity classes.

## Technologies and Concepts Applied

- [Hibernate](https://hibernate.org/orm/) 6.6;
- MySQL 8.0;
- JDK 18+;
- [iText PDF](https://itextpdf.com/) 8.0.3;

### Database

This project uses MySQL as a DBMS and, through Hibernate, performs object persistence and data management.

### Javadoc

Uses the maven-javadoc-plugin plugin to generate the javadoc for packaging purposes in the .jar executable (it also generates the website with the HTML documentation for the entire API code).

### Executable Generation

The project uses the maven-assembly-plugin plugin that generates an executable with all the dependencies and javadoc packaged in the same .jar.

### PDF Label Generation

Using iText, we provide an example of generating simple labels for sending correspondence via the post office.

### Internationalization

The system is currently available in Portuguese and English and is automatically displayed in the user's language according to the operating system language.

## Contributing

Pull requests are welcome. For large changes, please open an issue first to discuss the desired changes. For other collaborations, please provide details and motivations for your contribution in your pull request.

## License

[GNU GPLv3](https://choosealicense.com/licenses/gpl-3.0/)
