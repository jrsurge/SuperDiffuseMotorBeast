# SuperDiffuse_MotorBeast

This is an extension for SuperDiffuse that adds an adaptor class to convert from the motorBEAST to the control scheme used by SuperDiffuse.

After installing this Quark, you can create an adaptor for your controller very easily:
```supercollider
SuperDiffuse_MotorBeast("192.168.1.123", 57120);

// if you have a second (or third, etc.) controller, you can use the channelOffset argument to indicate the first control to target in SuperDiffuse
SuperDiffuse_MotorBeast("192.168.1.124", 57120, 32);
```

At this point, any information sent by that controller will be converted and sent to SuperDiffuse.

It's probably useful to add this line to your startup file so you always have the adaptor when you start SuperCollider, although you might have to change the IP address/port every now and then depending on how the motorBEAST assigns its address.
