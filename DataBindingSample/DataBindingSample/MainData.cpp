#include "pch.h"
#include "MainData.h"

using namespace Platform;
using namespace DataBindingSample;

MainData::MainData()
{
	m_MyTextValue = "MyText";
	m_MyFontSize = 12;
}

String^ MainData::MyTextValue::get()
{
	return m_MyTextValue;
}

void MainData::MyTextValue::set(String^ myTextValue)
{
	if (m_MyTextValue != myTextValue)
	{
		m_MyTextValue = myTextValue;
		NotifyPropertyChanged("MyTextValue");
	}
}

int MainData::MyFontSize::get()
{
	return m_MyFontSize;
}

void MainData::MyFontSize::set(int myFontSize)
{
	if (m_MyFontSize != myFontSize)
	{
		m_MyFontSize = myFontSize;
		NotifyPropertyChanged("MyFontSize");
	}
}

void MainData::NotifyPropertyChanged(String^ propertyName)
{
	PropertyChangedEventArgs^ args = ref new PropertyChangedEventArgs(propertyName);
	PropertyChanged(this, args);
}