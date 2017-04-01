using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataBindingSampleCSharp
{
    public class MainData : INotifyPropertyChanged
    {
        #region private members
        private string _myTextValue = "MyText";
        private int _myFontSize = 12;
        #endregion

        #region public properties
        public string MyTextValue
        {
            get 
            { 
                return _myTextValue; 
            }
            set 
            {
                if (_myTextValue == value) return;
                _myTextValue = value;
                NotifyPropertyChanged("MyTextValue");
            }
        }

        public int MyFontSize
        {
            get
            {
                return _myFontSize;
            }
            set
            {
                if (_myFontSize == value) return;
                _myFontSize = value;
                NotifyPropertyChanged("MyFontSize");
            }
        }
        #endregion

        #region Implementation of INotifyPropertyChanged
        public event PropertyChangedEventHandler PropertyChanged;

        private void NotifyPropertyChanged(string propertyName)
        {
            if (PropertyChanged != null)
                PropertyChanged(this, new PropertyChangedEventArgs(propertyName));
        }
        #endregion
    }
}
