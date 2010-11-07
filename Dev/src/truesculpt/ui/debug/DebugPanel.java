package truesculpt.ui.debug;

import truesculpt.main.R;
import truesculpt.utils.Utils;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DebugPanel extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.debug);

		final Button button = (Button) findViewById(R.id.show_sensors);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Utils.StartMyActivity(DebugPanel.this,
						truesculpt.ui.debug.DebugSensorsPanel.class);
			}
		});

		final Button button2 = (Button) findViewById(R.id.show_test);
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Utils.StartMyActivity(DebugPanel.this,
						truesculpt.ui.debug.DebugTestPanel.class);
			}
		});

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
}
