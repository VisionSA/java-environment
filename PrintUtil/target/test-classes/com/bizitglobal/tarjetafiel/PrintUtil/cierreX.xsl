<ticket>
	<header>
		<fecha>11/08/2009</fecha>
		<hora>10:50</hora>
		<recibo />
		<dni />
		<cliente />
		<operador>1212test, test</operador>
		<caja>00001</caja>
		<transaccion />
		<cuenta />
		<fechaApertura>07/08/2009</fechaApertura>
		<horaApertura>10:50</horaApertura>
	</header>
	<transacciones />
	<resumenes />
	<refinanciacion />
	<adelanto />
	<cierre tipo="X">
		<cheques hayCheques="true">
            <cheque>
                <numero>11111111</numero>
                <tipo>P</tipo>
                <importe>$100.00</importe>
            </cheque>
        </cheques>
        <cuenta nombre="0 - Cuentas" saldoFinal="260.0" ajuste="true">
			<medios>
				<medio>
					<id />
					<descripcion>APERTURA</descripcion>
					<importe>100.00</importe>
					<signo>DB</signo>
				</medio>
				<medio>
					<id />
					<descripcion>COBRANZA USUARIOS</descripcion>
					<importe>260.00</importe>
					<signo>DB</signo>
				</medio>
				<medio>
					<id />
					<descripcion>DESCARGA DE VALORES EFECTIVO</descripcion>
					<importe>-100.00</importe>
					<signo>CR</signo>
				</medio>
			</medios>
			<arqueos arqueoTotal="200.0" faltante-sobrante="FALTANTE -60.00">
				<arqueo>
					<descripcion>Pesos $50.00</descripcion>
					<cantidad>0</cantidad>
				</arqueo>
				<arqueo>
					<descripcion>Pesos $10.00</descripcion>
					<cantidad>0</cantidad>
				</arqueo>
				<arqueo>
					<descripcion>Pesos $0.10</descripcion>
					<cantidad>0</cantidad>
				</arqueo>
				<arqueo>
					<descripcion>Pesos $100.00</descripcion>
					<cantidad>2</cantidad>
				</arqueo>
				<arqueo>
					<descripcion>Pesos $20.00</descripcion>
					<cantidad>0</cantidad>
				</arqueo>
				<arqueo>
					<descripcion>Pesos $0.25</descripcion>
					<cantidad>0</cantidad>
				</arqueo>
				<arqueo>
					<descripcion>Pesos $2.00</descripcion>
					<cantidad>0</cantidad>
				</arqueo>
				<arqueo>
					<descripcion>Pesos $1.00</descripcion>
					<cantidad>0</cantidad>
				</arqueo>
				<arqueo>
					<descripcion>Pesos $5.00</descripcion>
					<cantidad>0</cantidad>
				</arqueo>
				<arqueo>
					<descripcion>Pesos $0.05</descripcion>
					<cantidad>0</cantidad>
				</arqueo>
				<arqueo>
					<descripcion>Pesos $0.50</descripcion>
					<cantidad>0</cantidad>
				</arqueo>
			</arqueos>
            <ajuste>
                <medio>
					<id />
					<descripcion>DESCARGA DE VALORES EFECTIVO</descripcion>
					<importe>-100.00</importe>
					<signo>CR</signo>
				</medio>
            </ajuste>
		</cuenta>
	</cierre>
</ticket>