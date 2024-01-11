

///////////////////////////////////////////////////////////////////////
//																	 //
// Codeger�st f�r Vorlesung Computergraphik WS 2023/24 �bung 2       //
//										                             //
///////////////////////////////////////////////////////////////////////

#include "vec.h"
#include "mat.h"
#include<cmath>
// might be you have to swith to
// #include "glut.h" depending on your GLUT installation
#include "glut.h"
#include<tuple>
using namespace std;

////////////////////////////////////////////////////////////
//
// system relevant global variables
//

// window width and height (choose an appropriate size)
const int g_iWidth = 600;
const int g_iHeight = 600;


// global variable to tune the timer interval
int g_iTimerMSecs;

float FOCUS = 300;
CVec4f eyePoint;
CVec4f viewDir;
CVec4f viewUp;

//
/////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////
//
// private, global variables ... replace by your own ones
//
// some global state variables used to describe ...
float g_iPos;			// ... position and ...
float g_iPosIncr;		// ... position increment (used in display1)

CVec2i g_vecPos;		// same as above but in vector form ...
CVec2i g_vecPosIncr;	// (used in display2)
///////////////////////////////////////////////////////////////

CVec3f cube1[8];

CVec3f cube2[8];
CVec3f cube3[8];

void setCubes() {
	/*
	CVec3f o;
	o(0) = 10; o(1) = 10; o(2) = -10; cube1[0] = o; //links unten
	o(0) = 10; o(1) = -10; o(2) = -10; cube1[1] = o;
	o(0) = -10; o(1) = -10; o(2) = -10; cube1[2] = o;
	o(0) = -10; o(1) = 10; o(2) = -10; cube1[3] = o;

	o(0) = 10; o(1) = 10; o(2) = -30; cube1[4] = o;
	o(0) = 10; o(1) = -10; o(2) = -30; cube1[5] = o;
	o(0) = -10; o(1) = -10; o(2) = -30; cube1[6] = o;
	o(0) = -10; o(1) = 10; o(2) = -30; cube1[7] = o;
	*/

	/*
	cube1[0] = (new float[3] { 40, 10, -10 }); // links unten
	cube1[1] = (new float[3] { 40, 70, -10 }); // links oben
	cube1[2] = (new float[3] { 90, 70, -10 }); // rechts oben
	cube1[3] = (new float[3] { 90, 10, -10 }); // rechts unten
	cube1[4] = (new float[3] { 40, 10, -30 }); // links unten
	cube1[5] = (new float[3] { 40, 70, -30 }); // links oben
	cube1[6] = (new float[3] { 90, 70, -30 }); // rechts oben
	cube1[7] = (new float[3] { 90, 10, -30 }); // rechts unten*/


	cube1[0] = (new float[3] { -50, 0, 0 }); // links unten
	cube1[1] = (new float[3] { -50, 50, 0 }); // links oben
	cube1[2] = (new float[3] { 0, 50, 0 }); // rechts oben
	cube1[3] = (new float[3] { 0, 0, 0 }); // rechts unten
	cube1[4] = (new float[3] { -50, 0, -50 }); // links unten
	cube1[5] = (new float[3] { -50, 50, -50 }); // links obens
	cube1[6] = (new float[3] { 0, 50, -50 }); // rechts oben
	cube1[7] = (new float[3] { 0, 0, -50 }); // rechts unten


	cube2[0] = (new float[3] { -45, -60, -30 }); // links unten
	cube2[1] = (new float[3] { -45, -30, -30 }); // links oben
	cube2[2] = (new float[3] { 20, -30, -30 }); // rechts oben
	cube2[3] = (new float[3] { 20, -60, -30 }); // rechts unten
	cube2[4] = (new float[3] { -45, -60, -55 }); // links unten
	cube2[5] = (new float[3] { -45, -30, -55 }); // links oben
	cube2[6] = (new float[3] { 20, -30, -55 }); // rechts oben
	cube2[7] = (new float[3] { 20, -60, -55 }); // rechts unten

	cube3[0] = (new float[3] { 40, 10, -10 }); // links unten
	cube3[1] = (new float[3] { 40, 70, -10 }); // links oben
	cube3[2] = (new float[3] { 90, 70, -10 }); // rechts oben
	cube3[3] = (new float[3] { 90, 10, -10 }); // rechts unten
	cube3[4] = (new float[3] { 40, 10, -30 }); // links unten
	cube3[5] = (new float[3] { 40, 70, -30 }); // links oben
	cube3[6] = (new float[3] { 90, 70, -30 }); // rechts oben
	cube3[7] = (new float[3] { 90, 10, -30 }); // rechts unten
}

void moveCube() {
	int moveX = 0;
	int moveY = 0;
	int moveZ = 0;
	for (int i = 0; i < 8; i++) {
		cube1[i](0) = cube1[i](0) + moveX;
		cube1[i](1) = cube1[i](1) + moveY;
		cube1[i](2) = cube1[i](2) + moveZ;
	}
}

// function to initialize our own variables
void init()
{
	setCubes();
	//moveCube();
	eyePoint = new float[4] { 0, 0, 0, 1 };
	viewDir = new float[4] { 0, 0, -1, 0 };
	viewUp = new float[4] { 0, 1, 0, 0 };
	// init timer interval
	g_iTimerMSecs = 10;

	// init variables for display1
	g_iPos = 0;
	g_iPosIncr = 2;

	// init variables for display2
	int aiPos[2] = { 0, 0 };
	int aiPosIncr[2] = { 2, 2 };
	g_vecPos.setData(aiPos);
	g_vecPosIncr.setData(aiPosIncr);
}

class Point {
public:

	Point(int x = 0, int y = 0) {
		this->x = x;
		this->y = y;
	}

	int x, y;
};

class Color {
public:

	Color(int r = 0, int g = 0, int b = 0) {
		this->r = r;
		this->g = g;
		this->b = b;
	}

	int r, g, b;
};

// function to initialize the view to ortho-projection
void initGL()
{
	glViewport(0, 0, g_iWidth, g_iHeight);	// Establish viewing area to cover entire window.

	glMatrixMode(GL_PROJECTION);			// Start modifying the projection matrix.
	glLoadIdentity();						// Reset project matrix.
	glOrtho(-g_iWidth / 2, g_iWidth / 2, -g_iHeight / 2, g_iHeight / 2, 0, 1);	// Map abstract coords directly to window coords.

	// tell GL that we draw to the back buffer and
	// swap buffers when image is ready to avoid flickering
	glDrawBuffer(GL_BACK);

	// tell which color to use to clear image
	glClearColor(0, 0, 0, 1);
}



void setPoint(Point p, Color c) {
	glBegin(GL_POINTS);
	glColor3f(c.r, c.g, c.b);	glVertex2i(p.x, p.y);
	glEnd();

}

//
// �BUNG 1 AUFGABE 1:
//
// Diese Funktion soll eine Gerade zwischen den Punkten
// p1 und p2 in der Farbe c malen. Benutzen Sie die Funktion
// setPoint um die individuellen Punkte zu zeichnen.
void bhamLine(Point p1, Point p2, Color c) {
	setPoint(p1, c);
	if (p1.x == p2.x && p1.y == p2.y) {
		return;
	}
	// erster Punkt

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

CVec4f projectZ(float fFocus, CVec4f pView) {
	CMat4f myMatrix;
	float g = 1 / -fFocus;
	myMatrix(0, 0) = 1;
	myMatrix(1, 1) = 1;
	myMatrix(3, 2) = g;
	myMatrix(3, 3) = 1;

	CVec4f t = myMatrix * pView;

	t(0) = t(0) * (1 / (1 - (pView(2) / fFocus)));
	t(1) = t(1) * (1 / (1 - (pView(2) / fFocus)));

	return t;
}

CMat4f getTransform(CVec4f viewOrigin, CVec4f viewDir, CVec4f viewUp) {
	// Achsen/Punkte des View-Systems
	CVec4f localViewDir = viewDir.normH();
	CVec4f localViewUp = viewUp.normH();
	CVec4f localViewLeft = localViewUp.crossH(-localViewDir).normH();

	CMat4f rotation;
	// Zeile 1 = Xv
	rotation(0, 0) = localViewLeft(0);
	rotation(0, 1) = localViewLeft(1);
	rotation(0, 2) = localViewLeft(2);
	//Zeile 2 = Yv
	rotation(1, 0) = localViewUp(0);
	rotation(1, 1) = localViewUp(1);
	rotation(1, 2) = localViewUp(2);
	//Zeile 3 = Zv
	rotation(2, 0) = localViewDir(0);
	rotation(2, 1) = localViewDir(1);
	rotation(2, 2) = localViewDir(2);

	rotation(3, 3) = 1;

	CVec4f translate = rotation * viewOrigin;

	//Spalte 3 der Matrix beschreibt die Verschiebung/Translation
	rotation(0, 3) = translate(0);
	rotation(1, 3) = translate(1);
	rotation(2, 3) = translate(2);

	return rotation;
}

CVec4f projectZallg(CMat4f transform, float focus, CVec4f pointW) {
	return projectZ(focus, transform * pointW);
}

void drawProjektedZ(CVec3f Points[8], Color c) {
	Point pointsP[8] = {};

	//Project Points
	for (int i = 0; i < 8; i++)
	{
		pointsP[i] = Point(Points[i](0), Points[i](1));
	}

	// Draw Points and Lines
	bhamLine(pointsP[0], pointsP[1], c);
	bhamLine(pointsP[1], pointsP[2], c);
	bhamLine(pointsP[2], pointsP[3], c);
	bhamLine(pointsP[3], pointsP[0], c);

	bhamLine(pointsP[4], pointsP[5], c);
	bhamLine(pointsP[5], pointsP[6], c);
	bhamLine(pointsP[6], pointsP[7], c);
	bhamLine(pointsP[7], pointsP[4], c);

	bhamLine(pointsP[0], pointsP[4], c);
	bhamLine(pointsP[1], pointsP[5], c);
	bhamLine(pointsP[2], pointsP[6], c);
	bhamLine(pointsP[3], pointsP[7], c);
}

void drawQuader(CVec3f quader[8], float fFocus, Color c) {
	CVec3f pointsV[8];
	// getTransform * Cube => Cube von WeltCords in ViewCords
	CMat4f transform = getTransform(eyePoint, viewDir, viewUp);

	for (int i = 0; i < 8; i++) {
		CVector<float, 4> pointW;
		pointW(0) = quader[i](0);
		pointW(1) = quader[i](1);
		pointW(2) = quader[i](2);
		pointW(3) = 1;

		CVec4f pointV = projectZallg(transform, fFocus, pointW);

		CVector<float, 3> pointV3;
		pointV3(0) = pointV(0);
		pointV3(1) = pointV(1);
		pointV3(2) = 1;

		pointsV[i] = pointV3;
	}
	// auf ViewPlane projecten, zeichnen
	drawProjektedZ(pointsV, c);
}

void rotate(float angle, char axis) {
	angle = angle * 3.141 / 180;
	CMat4f rotationM;
	if (axis == 'x') {
		rotationM(0, 0) = 1;
		rotationM(1, 1) = (float)cos(angle);
		rotationM(1, 2) = (float)-sin(angle);
		rotationM(2, 1) = (float)sin(angle);
		rotationM(2, 2) = (float)cos(angle);
		rotationM(3, 3) = 1;
	}
	else if (axis == 'y') {
		rotationM(0, 0) = cos(angle);
		rotationM(0, 2) = sin(angle);
		rotationM(1, 1) = 1;
		rotationM(2, 0) = -sin(angle);
		rotationM(2, 2) = cos(angle);
		rotationM(3, 3) = 1;
	}
	else if (axis == 'z') {
		rotationM(0, 0) = (float)cos(angle);
		rotationM(0, 1) = (float)-sin(angle);
		rotationM(1, 1) = (float)cos(angle);
		rotationM(1, 0) = (float)sin(angle);
		rotationM(2, 2) = 1;
		rotationM(3, 3) = 1;
	}
	eyePoint = eyePoint * rotationM;
	viewDir = viewDir * rotationM;
	viewUp = viewUp * rotationM;
}
void rotateCam(float angle, char axis) {
	angle = angle * 3.141 / 180;
	CMat4f rotationM;
	if (axis == 'x') {
		rotationM(0, 0) = 1;
		rotationM(1, 1) = (float)cos(angle);
		rotationM(1, 2) = (float)-sin(angle);
		rotationM(2, 1) = (float)sin(angle);
		rotationM(2, 2) = (float)cos(angle);
		rotationM(3, 3) = 1;
	}
	else if (axis == 'y') {
		rotationM(0, 0) = cos(angle);
		rotationM(0, 2) = sin(angle);
		rotationM(1, 1) = 1;
		rotationM(2, 0) = -sin(angle);
		rotationM(2, 2) = cos(angle);
		rotationM(3, 3) = 1;
	}
	else if (axis == 'z') {
		rotationM(0, 0) = (float)cos(angle);
		rotationM(0, 1) = (float)-sin(angle);
		rotationM(1, 1) = (float)cos(angle);
		rotationM(1, 0) = (float)sin(angle);
		rotationM(2, 2) = 1;
		rotationM(3, 3) = 1;
	}
	eyePoint = eyePoint * rotationM;
}

int min(int a, int b) { return a > b ? a : b; }
// timer callback function
void timer(int value)
{
	///////
	// update your variables here ...
	//

	int size2 = min(g_iWidth, g_iHeight) / 2;

	// variables for display1 ...
	if (g_iPos <= -size2 || g_iPos >= size2) g_iPosIncr = -g_iPosIncr;
	g_iPos += g_iPosIncr;

	// variables for display2 ...
	if (g_vecPos(1) <= -size2 || g_vecPos(1) >= size2) g_vecPosIncr = -g_vecPosIncr;
	g_vecPos += g_vecPosIncr;

	//
	///////

	// the last two lines should always be
	glutPostRedisplay();
	glutTimerFunc(g_iTimerMSecs, timer, 0);	// call timer for next iteration
}

// display callback function
void display1(void)
{

	glClear(GL_COLOR_BUFFER_BIT);

	drawQuader(cube1, FOCUS, Color(1, 0, 0));
	drawQuader(cube2, FOCUS, Color(0, 1, 0));
	drawQuader(cube3, FOCUS, Color(0, 0, 1));

	// In double buffer mode the last
	// two lines should alsways be
	glFlush();
	glutSwapBuffers(); // swap front and back buffer
}

// display callback function
void display2(void)
{
	glClear(GL_COLOR_BUFFER_BIT);

	///////
	// display your data here ...
	//

	glBegin(GL_QUADS);
	glColor3f(1, 0, 0);	glVertex2i(-g_vecPos(1), -g_vecPos(2));
	glColor3f(0, 1, 0);	glVertex2i(g_vecPos(1), -g_vecPos(2));
	glColor3f(0, 0, 1);	glVertex2i(g_vecPos(1), g_vecPos(2));
	glColor3f(1, 1, 0);	glVertex2i(-g_vecPos(1), g_vecPos(2));
	glEnd();

	// In double buffer mode the last
	// two lines should alsways be
	glFlush();
	glutSwapBuffers(); // swap front and back buffer
}

void keyboard(unsigned char key, int x, int y)
{
	float angle = 5;
	switch (key) {
	case 'q':
	case 'Q':
		exit(0); // quit program
		break;
	case 'u':
		eyePoint(0)--;
		break;
	case 'U':
		eyePoint(0)++;
		break;
	case 'v':
		eyePoint(1)--;
		break;
	case 'V':
		eyePoint(1)++;
		break;
	case 'w':
		eyePoint(2)--;
		break;
	case 'W':
		eyePoint(2)++;
		break;
	case 'X':
		rotate(angle, 'x');
		break;
	case 'Y':
		rotate(angle, 'y');
		break;
	case 'Z':
		rotate(angle, 'z');
		break;
	case 'x':
		rotate(-angle, 'x');
		break;
	case 'y':
		rotate(-angle, 'y');
		break;
	case 'z':
		rotate(-angle, 'z');
		break;
	case 'r':
		eyePoint = new float[4] { 0, 0, 0, 1 };
		viewDir = new float[4] { 0, 0, -1, 0 };
		viewUp = new float[4] { 0, 1, 0, 0 };
		break;
	case 'a':
		rotateCam(angle, 'x');
		break;
	case 'A':
		rotateCam(-angle, 'x');
		break;
	case 'b':
		rotateCam(angle, 'y');
		break;
	case 'B':
		rotateCam(-angle, 'y');
		break;
	case 'c':
		rotateCam(angle, 'z');
		break;
	case 'C':
		rotateCam(-angle, 'z');
		break;
	default:
		// do nothing ...
		break;
	};
}

int main(int argc, char** argv)
{
	glutInit(&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
	glutCreateWindow("Computergrafik �bung 2 (WS23/24)");
	glutReshapeWindow(800, 800);
	init();	// init my variables first
	initGL();	// init the GL (i.e. view settings, ...)

	// assign callbacks
	glutTimerFunc(10, timer, 0);
	glutKeyboardFunc(keyboard);
	glutDisplayFunc(display1);
	// you might want to add a resize function analog to
	// �bung1 using code similar to the initGL function ...

	// start main loop
	glutMainLoop();

	return 0;
}