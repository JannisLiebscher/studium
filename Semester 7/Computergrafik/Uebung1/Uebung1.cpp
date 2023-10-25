///////////////////////////////////////////////////////////////////////
//																	 //
// Codegerüst für Vorlesung Computergraphik WS 2023/24 �bung 1       //
//										                             //
///////////////////////////////////////////////////////////////////////


// Include-File für die Text Ein-/Ausgabe
#include <iostream>
using namespace std;


// Include-File für die GLUT-Library
#include "glut.h"



/////////////////////////////////////////////////////////////////////////////////////////
//
// Hier wird einiges initialisiert. 
//
//


// Aufl�sungen der gesamten Textur
// !!!ACHTUNG!!! nicht alle Texturaufl�sungen funktionieren!
// Stichwort ungefähr: POT2 Problematik
#define TEX_RES_X 60
#define TEX_RES_Y 60
// Anzahl der Pixel der Textur
#define TEX_RES TEX_RES_X*TEX_RES_Y
// Achsenlänge der Textur (Achsen sind asymmetrisch von -TexRes#/2 bis TesRes#/2-1)
#define TEX_HALF_X TEX_RES_X/2
#define TEX_HALF_Y TEX_RES_Y/2
// Konvertiert Indices von (x,y) Koordinaten in ein lineares Array
#define TO_LINEAR(x, y) (((x)) + TEX_RES_X*((y)))

// globaler Speicher für Texturdaten
char g_Buffer[3 * TEX_RES];
// Textur ID Name
GLuint g_TexID = 0;

// Auflösung des Hauptfensters (kann sich durch User ändern)
int g_WinWidth = 800;
int g_WinHeight = 800;

// Funktion organisiert die Textur.
// Kümmern Sie sich nicht weiter um diese Funktion, da
// sie momentan nur ein notwendiges übel darstellt!
void manageTexture()
{
	glEnable(GL_TEXTURE_2D);
	if (g_TexID == 0) glGenTextures(1, &g_TexID);
	glBindTexture(GL_TEXTURE_2D, g_TexID);
	glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_REPLACE);
	glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
	glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
	glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);
	glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
	glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, TEX_RES_X, TEX_RES_Y, 0, GL_RGB, GL_UNSIGNED_BYTE, g_Buffer);
	glBindTexture(GL_TEXTURE_2D, 0);
	glDisable(GL_TEXTURE_2D);
}

// Callback Funktion um die Fenstergrößen anzupassen.
// Auch diese Funktion ist ein notwendiges übel! Kümmern
// Sie sich im Moment nicht weiter darum.
void reshape(int w, int h)
{

	g_WinWidth = w;
	g_WinHeight = h;
	glViewport(0, 0, w, h);					// Establish viewing area to cover entire window.

	glMatrixMode(GL_PROJECTION);			// Start modifying the projection matrix.
	glLoadIdentity();						// Reset project matrix.
	glOrtho(-w / 2, w / 2, -h / 2, h / 2, 0, 1);	// Map abstract coords directly to window coords.

	glutPostRedisplay();
}


//
//
/////////////////////////////////////////////////////////////////////////////////////////
//
// Hier fängt der für Sie wirklich relevante Teil des Programms an.
//


// Eine überaus primitive Punktklasse
class Point {
public:

	Point(int x = 0, int y = 0) {
		this->x = x;
		this->y = y;
	}

	int x, y;
};

// Eine überaus primitive Farbklasse
class Color {
public:

	Color(float r = 1.0f, float g = 1.0f, float b = 1.0f) {
		this->r = r;
		this->g = g;
		this->b = b;
	}

	float r, g, b;
};

// Funktion löscht den Bildschirm mit der angegebenen Farbe
// Usage z.B.: clearImage (Color (1,1,1))
// löscht den Bildschirm in Wei�.
// Ohne Farbangabe ist der Standard Wei�
void clearImage(Color c = Color()) {
	for (int i = 0; i < TEX_RES; i++) {
		g_Buffer[3 * i] = int(255.0 * c.r);
		g_Buffer[3 * i + 1] = int(255.0 * c.g);
		g_Buffer[3 * i + 2] = int(255.0 * c.b);
	}
}

// Funktion malt einen Punkt an die angegebenen Koordinaten
// Usage z.B.: setPoint (Point(10, 5), Color (1,0,0))
// malt einen Punkt an die Koordinate (10, 5)
// Ohne Farbangabe ist die Standard-Malfarbe Schwarz
//
// Nutzen Sie diese Funktion ...
void setPoint(Point p, Color c = Color(0, 0, 0)) {
	int x = p.x + TEX_HALF_X;
	int y = p.y + TEX_HALF_Y;
	if (x < 0 || y < 0 || x >= TEX_RES_X || y >= TEX_RES_Y) {
		cerr << "Illegal point co-ordinates (" << p.x << ", " << p.y << ")\n" << flush;
		return;
	}

	g_Buffer[3 * TO_LINEAR(x, y)] = int(255.0 * c.r);
	g_Buffer[3 * TO_LINEAR(x, y) + 1] = int(255.0 * c.g);
	g_Buffer[3 * TO_LINEAR(x, y) + 2] = int(255.0 * c.b);
}

//
// ÜBUNG 1 AUFGABE 1:
//
// Diese Funktion soll eine Gerade zwischen den Punkten
// p1 und p2 in der Farbe c malen. Benutzen Sie die Funktion
// setPoint um die individuellen Punkte zu zeichnen.
void bhamLine(Point p1, Point p2, Color c) {

	// erster Punkt
	setPoint(p1, c);
	int x1 = p1.x;
	int y1 = p1.y;
	int x2 = p2.x;
	int y2 = p2.y;

	int deltaX = x2 - x1;
	int deltaY = y2 - y1;

	// von links nach rechts?
	bool lr = deltaX >= 0;

	// Punkte vertauschen falls die Gerade von rechts nach links gezeichnet wird
	if (!lr) {
		swap(x1, x2);
		swap(y1, y2);

		deltaX = x2 - x1;
		deltaY = y2 - y1;
	}

	// Steigung positiv?
	bool sPos = deltaY >= 0;

	// an x spiegeln wenn die Steigung negativ ist
	if (!sPos) {
		y2 = -y2;
		y1 = -y1;

		deltaX = x2 - x1;
		deltaY = y2 - y1;
	}

	// Steigung kleiner 1?
	bool kl1 = deltaX >= deltaY;

	// bei beiden Punkten x und y verstauschen wenn die Steigung größer 1 ist
	if (!kl1) {
		swap(x1, y1);
		swap(x2, y2);

		deltaX = x2 - x1;
		deltaY = y2 - y1;
	}

	int deltaNE = 2 * (deltaY - deltaX);
	int deltaE = 2 * deltaY;
	int d = 2 * deltaY - deltaX;


	int x = x1;
	int y = y1;

	int plotX;
	int plotY;

	while (x < x2) {
		if (d >= 0) {
			d += deltaNE;
			x++;
			y++;
		}
		else {
			d += deltaE;
			x++;
		}
		plotX = x;
		plotY = y;

		if (!kl1) {
			swap(plotY, plotX);
			if (!sPos) {
				plotY = -x;
			}
		}
		else if (!sPos) {
			plotY = -y;
		}
		setPoint(Point(plotX, plotY), c);
	}

	// letzter Punkt
	setPoint(p2, c);
}

//
// ÜBUNG 1 AUFGABE 2:
//
// Diese Funktion soll einen Kreis mit Radius r um den Punkt p
// malen. Benutzen Sie die Funktion setPoint um die individuellen
// Punkte zu zeichnen. Vergessen Sie nicht auch den Mittelpunkt zu
// zeichnen!
void bhamCircle(Point p, int r, Color c) {

	// Mittelpunkt
	setPoint(p, c);

	// ...
}

void stuff() {
	manageTexture();

	glClear(GL_COLOR_BUFFER_BIT);
	glBindTexture(GL_TEXTURE_2D, g_TexID);
	glEnable(GL_TEXTURE_2D);

	glBegin(GL_QUADS);
	glColor3f(1, 0, 0);
	glTexCoord2f(0, 0); glVertex2f(-g_WinWidth / 2.0f, -g_WinHeight / 2.0f);
	glTexCoord2f(1, 0);	glVertex2f(g_WinWidth / 2.0f, -g_WinHeight / 2.0f);
	glTexCoord2f(1, 1);	glVertex2f(g_WinWidth / 2.0f, g_WinHeight / 2.0f);
	glTexCoord2f(0, 1);	glVertex2f(-g_WinWidth / 2.0f, g_WinHeight / 2.0f);
	glEnd();

	glBindTexture(GL_TEXTURE_2D, 0);
	glDisable(GL_TEXTURE_2D);

	glFlush();
}

// Die Callback Funktion die für das eigentliche Malen
// zuständig ist. Im Wesentlichen brauchen Sie sich nur
// um den Bereich zwischen den Kommentaren zu k�mmern,
// alles andere ist wiederum ein notwendiges übel!
void display(void)
{
	Color background(0.5, 0.5, 0.5);	// grauer Hintergrund
	clearImage(background);				// alte Anzeige l�schen

	//////////////////////////////////////////////////////////////////
	//
	// Hier sollen Ihre Bresenham-Funktionen
	// eine Gerade und einen Kreis zeichnen.
	//
	// 

	// Im Prinzip brauchen Sie hier keine �nderungen vorzunehmen, 
	// es sei denn Sie wollen "spielen" :-D
	//

	//Point p1(-10, 20);				// ersten Punkt f�r Gerade definieren
	//Point p2(20, -15);				// ebenso den zweiten Punkt

	setPoint(Point(0, 0));
	Point p1(0, 0);


	Point points[12] = {
		Point(20, 0),
		Point(17, 10),
		Point(10, 17),
		Point(0, 20),
		Point(-10, 17),
		Point(-17, 10),
		Point(-20,0),
		Point(-17, -10),
		Point(-10, -17),
		Point(0, -20),
		Point(10, -17),
		Point(17, -10),
	};
	Color cRed(1, 0, 0);// Es soll eine rote Gerade sein ...

	int i = 0;
	while (true) {
		clearImage(background);
		bhamLine(p1, points[i], cRed);
		i++;
		if (i == 12) {
			i = 0;
		}
		Sleep(500);
		stuff();
	}


	//bhamLine  (p1, p2, cRed);			// Gerade zeichnen ...

	//setPoint(p1);
	//setPoint(p2);

	Point p(-3, -5);					// Mittelpunkt für Kreis definieren
	int r = 17;							// Radius festlegen
	Color cBlue(0, 0, 1);					// Es soll ein blauer Kreis sein ...
	//bhamCircle (p, r, cBlue);			// Kreis zeichnen ...

	//
	// Ab hier sollten Sie nichts mehr �ndern!
	//
	//////////////////////////////////////////////////////////////////


}


// Die Main-Funktion
int main(int argc, char** argv) {

	glutInit(&argc, argv);
	glutInitWindowSize(g_WinWidth, g_WinHeight);
	glutCreateWindow("Computergrafik �bung 1: Bresenham (WS23/24)");

	glutReshapeFunc(reshape);	// zuständig für Größen�nderungen des Fensters
	glutDisplayFunc(display);	// zuständig für das wiederholte Neuzeichnen des Bildschirms

	glutMainLoop();

	glDeleteTextures(1, &g_TexID); // löscht die oben angelegte Textur

	return 0;
}
