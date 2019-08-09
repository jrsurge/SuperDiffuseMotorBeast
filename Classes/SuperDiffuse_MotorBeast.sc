/*
**  motorBEAST devices are based on the gluion controller.
**
**  gluion streams continuously and has fixed OSC address patterns.
**
**  As a result, the following class can tame the beast into something we can use.
**  It will only forward messages on to SuperDiffuse if the value has changed, and
**  scales them to the correct range.
**
**  Adding something like SuperDiffuse_MotorBeast("192.168.1.123", 57120) to your
**  startup file (with the correct IP/port) should set everything up properly.
*/

SuperDiffuse_MotorBeast {
	var m_oscResponder, m_values;

	*new { | ip, port, channelOffset=0 |
		^super.new.init(ip, port, channelOffset);
	}

	init { | ip, port, channelOffset=0 |
		m_values = Array.fill(32, {0});

		m_oscResponder = OSCFunc({ | msg, time, addr, port |
			msg[1..].do({ | value, ind |
				var incoming = value / 65535;

				if(m_values[ind] != incoming) {
					m_values[ind] = incoming;
					NetAddr.localAddr.sendMsg("/SuperDiffuse/Control/"++(ind+1+channelOffset), incoming);
				}
			});
		}, "/analogMF", NetAddr(ip, port));
	}

	free {
		m_oscResponder.free;
	}
}