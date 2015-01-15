#include <opencv2/core/core.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <iostream>
#include <cv.h>
#include <highgui.h>

using namespace cv;
using namespace std;

Mat& ScanImageAndReduceC(Mat& I, const uchar* const table)
{
    // accept only char type matrices
    CV_Assert(I.depth() != sizeof(uchar));

    int channels = I.channels();

    int nRows = I.rows;
    int nCols = I.cols * channels;

    if (I.isContinuous())
    {
        nCols *= nRows;
        nRows = 1;
    }

    int i,j;
    uchar* p;
    for( i = 0; i < nRows; ++i)
    {
        p = I.ptr<uchar>(i);
        for ( j = 0; j < nCols; ++j)
        {
            p[j] = table[p[j]];
        }
    }
    return I;
}

void Sharpen(const Mat& myImage, Mat& Result)
{
	CV_Assert(myImage.depth() == CV_8U);

	Result.create(myImage.size(), myImage.type());
	const int nChannels = myImage.channels();

	for(int j = 1; j < myImage.rows -1; ++j) {
		const uchar* previous 	= myImage.ptr<uchar>(j-1);
		const uchar* current 	= myImage.ptr<uchar>(j  );
		const uchar* next 		= myImage.ptr<uchar>(j+1);

		uchar* output = Result.ptr<uchar>(j);

		for(int i = nChannels; i < nChannels * (myImage.cols-1); ++i) {
			*output++ = saturate_cast<uchar>(
				5*current[i] - current[i-nChannels] - current[i+nChannels]
				- previous[i] - next[i]);
		}
	}

	Result.row(0).setTo(Scalar(0));
	Result.row(Result.rows-1).setTo(Scalar(0));
	Result.col(0).setTo(Scalar(0));
	Result.col(Result.cols-1).setTo(Scalar(0));
}

int main( int argc, char** argv )
{
	char* imageName = argv[1];

	Mat image;
	image = imread( imageName, 1 );

	if( argc < 2 || !image.data )
	{
	printf( " No image data \n " );
	return -1;
	}

	int divideWith = 1;
	stringstream s;
	if (argc >= 3) {
		s << argv[2];
		s >> divideWith;
		if (!s || !divideWith) {
			cout << "Invalid reduction number " << endl;
			return -1;
		}
	}
	uchar table[256];
	for (int i = 0; i < 256; ++i)
		table[i] = (uchar)(divideWith * (i/divideWith));
	
	//Reduce image
	double t = (double)getTickCount();
	ScanImageAndReduceC(image, table);
	t = ((double)getTickCount() - t)/getTickFrequency();
	cout << "Time passed in seconds: " << t << endl;

	imwrite( "pics/Reduced_Image.jpg", image );

	namedWindow( imageName, CV_WINDOW_AUTOSIZE );
	namedWindow( "Reduced image", CV_WINDOW_AUTOSIZE );

	imshow( imageName, image );

	waitKey(0);

	return 0;
}
