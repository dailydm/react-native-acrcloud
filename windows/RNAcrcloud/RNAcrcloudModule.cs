using ReactNative.Bridge;
using System;
using System.Collections.Generic;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;

namespace Acrcloud.RNAcrcloud
{
    /// <summary>
    /// A module that allows JS to share data.
    /// </summary>
    class RNAcrcloudModule : NativeModuleBase
    {
        /// <summary>
        /// Instantiates the <see cref="RNAcrcloudModule"/>.
        /// </summary>
        internal RNAcrcloudModule()
        {

        }

        /// <summary>
        /// The name of the native module.
        /// </summary>
        public override string Name
        {
            get
            {
                return "RNAcrcloud";
            }
        }
    }
}
