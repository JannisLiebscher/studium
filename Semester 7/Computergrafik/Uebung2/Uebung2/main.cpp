///////////////////////////////////////////////////////////////////////
//																	 //
// Codegerüst für Vorlesung Computergraphik WS 2023/24 Übung 2       //
//										                             //
///////////////////////////////////////////////////////////////////////

#include "vec.h"
#include "mat.h"
#include<cmath>
#include<tuple>
# define M_PI           3.14159265358979323846  /* pi */

// might be you have to swith to
// #include "glut.h" depending on your GLUT installation
#include "glut.h"
using namespace std;

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


// window width and height (choose an appropriate size)
const int g_iWidth  = 400;
const int g_iHeight = 400;

// global variable to tune the timer interval
int g_iTimerMSecs;

const Point sun = Point(0, 0);
const int rSun = 50;
const int rEarth = 20;
const int rMoon = 10;

float g_iPos;			// ... position and ...
float g_iPosIncr;		// ... position increment (used in display1)

const Color cSun = Color(1, 1, 0);
const Color cEarth = Color(0, 0, 1);
const Color cMoon = Color(1, 1, 1);

const double stepEarth = 3.0 * M_PI / 180.0;
const double stepMoon = 25.0 * M_PI / 180.0;

// affine
Point earth;
tuple<float, float> fEarth;
Point moon;
Point earthOld;
tuple<float, float> fMoon;

// Homogeneous
Point earthH;
tuple<float, float> fEarthH;
Point moonH;
Point earthOldH;
tuple<float, float> fMoonH;



CVec2i g_vecPos;		// same as above but in vector form ...
CVec2i g_vecPosIncr;	// (used in display2)


// function to initialize our own variables
void init () 
{

	// init timer interval
	g_iTimerMSecs = 100;

	// init variables for display1
	earth = Point(sun.x + 150, 0);
	fEarth = make_tuple(earth.x, earth.y);
	moon = Point(earth.x + 50, 0);
	fMoon = make_tuple(moon.x, moon.y);


	// init variables for display2
	earthH = Point(sun.x + 150, 0);
	fEarthH = make_tuple(earth.x, earth.y);
	moonH = Point(earth.x + 50, 0);
	fMoonH = make_tuple(moon.x, moon.y);

}
void setPoint(Point p, Color c) {
	glBegin(GL_POINTS);
	glColor3f(c.r, c.g, c.b);	glVertex2i(p.x, p.y);
	glEnd();

}
void translatePoint(Point plotPoint, Point center, Color c) {
	setPoint(Point(plotPoint.x + center.x, plotPoint.y + center.y), c);
}
tuple<float, float> translatePointBack(tuple<float, float> p, Point center) {
	return make_tuple(get<0>(p) + center.x, get<1>(p) + center.y);
}
tuple<float, float> translatePointTo(tuple<float, float> p, Point center) {
	return make_tuple(get<0>(p) - center.x, get<1>(p) - center.y);
}

tuple<float,float> rotateOriginPoint(tuple<float,float> p,double step) {
	CMat2f myMatrix;
	myMatrix(0, 0) = cos(step);
	myMatrix(0, 1) = -sin(step);
	myMatrix(1, 0) = sin(step);
	myMatrix(1, 1) = cos(step);

	CVector<float, 2> myVector;

	myVector(0) = get<0>(p);
	myVector(1) = get<1>(p);

	CVector<float, 3> r =  myMatrix * myVector;

	return make_tuple(r(0),r(1));
}

tuple<float, float> rotateArbitraryPoint(tuple<float, float> p,Point center,double step) {
	tuple<float, float> translated = translatePointTo(p, center);
	tuple<float, float> rotated = rotateOriginPoint(translated,step);
	return translatePointBack(rotated,center);
}


tuple<float, float> rotatePointH(tuple<float, float> p, double step,Point center) {

	//Rotate
	CMat3f myMatrix;
	myMatrix(0, 0) = cos(step);
	myMatrix(0, 1) = -sin(step);
	myMatrix(1, 0) = sin(step);
	myMatrix(1, 1) = cos(step);
	myMatrix(2, 2) = 1;

	//TranslateTO
	CMat3f trMatrixTo;
	trMatrixTo(0, 0) = 1;
	trMatrixTo(0, 2) = center.x;
	trMatrixTo(1, 1) = 1;
	trMatrixTo(1, 2) = center.y;
	trMatrixTo(2, 2) = 1;

	//TranslateBack
	CMat3f trMatrixBack = trMatrixTo;
	trMatrixBack(0, 2) = -center.x;
	trMatrixBack(1, 2) = -center.y;

	CVector<float, 3> myVector;
	myVector(0) = get<0>(p);
	myVector(1) = get<1>(p);
	myVector(2) = 1;

	CVector<float, 2> r = trMatrixTo*myMatrix* trMatrixBack * myVector;

	return make_tuple(r(0), r(1));
}


void bhamCircle(Point p, int r, Color c) {

	int xx = p.x;
	int yy = p.y;

	int x, y, d, deltaSE, deltaE;
	x = 0;
	y = r;
	d = 5 - 4 * r;
	translatePoint(Point(x, y), p, c);
	translatePoint(Point(x, -y), p, c);
	translatePoint(Point(y, x), p,c);
	translatePoint(Point(-y, x), p,c);

	while (y > x) {
		if (d >= 0) {
			deltaSE = 4 * (2 * (x - y) + 5);
			d += deltaSE;
			x++;
			y--;
		}
		else {
			deltaE = 4 * (2 * x + 3);
			d += deltaE;
			x++;
		}

		translatePoint(Point(x, y), p, c);
		translatePoint(Point(x, -y), p, c);
		translatePoint(Point(y, x),  p, c);
		translatePoint(Point(-y, x), p, c);

		translatePoint(Point(-x, -y), p, c);
		translatePoint(Point(-x, y), p, c);
		translatePoint(Point(-y, -x), p, c);
		translatePoint(Point(y, -x), p, c);
	}

}

// function to initialize the view to ortho-projection
void initGL () 
{
	glViewport (0, 0, g_iWidth, g_iHeight);	// Establish viewing area to cover entire window.

	glMatrixMode (GL_PROJECTION);			// Start modifying the projection matrix.
	glLoadIdentity ();						// Reset project matrix.
	glOrtho (-g_iWidth/2, g_iWidth/2, -g_iHeight/2, g_iHeight/2, 0, 1);	// Map abstract coords directly to window coords.

	// tell GL that we draw to the back buffer and
	// swap buffers when image is ready to avoid flickering
	glDrawBuffer (GL_BACK);

	// tell which color to use to clear image
	glClearColor (0,0,0,1);
}


int min (int a, int b) { return a>b? a: b; }
// timer callback function
void timer (int value) 
{
	///////
	// update your variables here ...
	//

	int size2 = min (g_iWidth, g_iHeight) / 2;

	// variables for display1 ...
	earthOld = earth;
	fEarth = rotateOriginPoint(fEarth,stepEarth);
	earth = Point(round(get<0>(fEarth)), round(get<1>(fEarth)));
	
	get<0>(fMoon) = get<0>(fMoon) + earth.x - earthOld.x;
	get<1>(fMoon) = get<1>(fMoon) + earth.y - earthOld.y;

	fMoon = rotateArbitraryPoint(fMoon, earth,stepMoon);
	moon = Point(round(get<0>(fMoon)), round(get<1>(fMoon)));
	

	// variables for display2 ...
	earthOldH = earthH;
	fEarthH = rotatePointH(fEarthH, stepEarth,Point(0,0));
	earthH = Point(round(get<0>(fEarthH)), round(get<1>(fEarthH)));

	get<0>(fMoonH) = get<0>(fMoonH) + earthH.x - earthOldH.x;
	get<1>(fMoonH) = get<1>(fMoonH) + earthH.y - earthOldH.y;

	fMoonH = rotatePointH(fMoonH, stepMoon, earthH);
	moonH = Point(round(get<0>(fMoonH)), round(get<1>(fMoonH)));

	// the last two lines should always be
	glutPostRedisplay ();
	glutTimerFunc (g_iTimerMSecs, timer, 0);	// call timer for next iteration
}

// display callback function
void display1 (void) 
{
	glClear (GL_COLOR_BUFFER_BIT);

	// Sun
	bhamCircle(sun, rSun,cSun);
	bhamCircle(earth, rEarth,cEarth);
	bhamCircle(moon, rMoon,cMoon);

	glFlush ();
	glutSwapBuffers (); // swap front and back buffer
}

// display callback function
void display2 (void) 
{
	glClear(GL_COLOR_BUFFER_BIT);

	// Sun
	bhamCircle(sun, rSun, Color(1,0,0));
	bhamCircle(earthH, rEarth, cEarth);
	bhamCircle(moonH, rMoon, cMoon);

	glFlush();
	glutSwapBuffers(); // swap front and back buffer
}

void keyboard (unsigned char key, int x, int y) 
{
	switch (key) {
		case 'q':
		case 'Q':
			exit (0); // quit program
			break;
		case '1':
			glutDisplayFunc (display1);
			//glutPostRedisplay ();	// not needed since timer triggers redisplay
			break;
		case '2':
			glutDisplayFunc (display2);
			//glutPostRedisplay ();	// not needed since timer triggers redisplay
			break;
		default:
			// do nothing ...
			break;
	};
}

/*
void bhamLine (Point p1, Point p2, Color c) 
{
	glBegin (GL_POINTS);
	glColor3f (c.r, c.g, c.b);
	// ...

		// implement bhamLine here and use
		// glVertex2i (x, y);
		// to draw a pixel
	
	// ...
	glEnd ();
}
*/



int main (int argc, char **argv) 
{
	glutInit (&argc, argv);
	glutInitDisplayMode(GLUT_DOUBLE|GLUT_RGB);
	glutCreateWindow("Computergrafik Übung 2 (WS23/24)");

	init  ();	// init my variables first
	initGL();	// init the GL (i.e. view settings, ...)

	// assign callbacks
	glutTimerFunc (10, timer, 0);
	glutKeyboardFunc (keyboard);
	glutDisplayFunc (display1);
	// you might want to add a resize function analog to
	// Übung1 using code similar to the initGL function ...

	// start main loop
	glutMainLoop ();

	return 0;
}

