#pragma once

using namespace Platform;
using namespace Windows::UI::Xaml::Data;

namespace DataBindingSample
{
	[Bindable]
	public ref class MainData sealed : INotifyPropertyChanged
	{

	public:
		MainData();

		property String^ MyTextValue
		{
			String^ get();
			void set(String^);
		}

		property int MyFontSize
		{
			int get();
			void set(int);
		}

		virtual event PropertyChangedEventHandler^ PropertyChanged;

	private:
		void NotifyPropertyChanged(String^);

		String^ m_MyTextValue;
		int m_MyFontSize;

	};
}

