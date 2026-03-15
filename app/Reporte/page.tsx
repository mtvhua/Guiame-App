"use client";

import { useState } from "react";
import Navbar from "../../app/Components/Navbar";

export default function Reporte() {

  const [nota, setNota] = useState("");
  const [fecha, setFecha] = useState<string>(new Date().toISOString().split("T")[0]); // fecha actual
  const [notas, setNotas] = useState<{ texto: string; fecha: string }[]>([]);

  const agregarNota = () => {
    if (nota.trim() === "") return;

    setNotas([...notas, { texto: nota, fecha }]);
    setNota("");
    setFecha(new Date().toISOString().split("T")[0]); // reset fecha a hoy
  };

  return (
    <main className="min-h-screen bg-white">

      <Navbar />

      <div className="max-w-6xl mx-auto mt-10 px-4 grid md:grid-cols-2 gap-10">

        {/* REGISTRO */}
        <div className="bg-gray-100 rounded-2xl shadow-lg p-8">

          <h1 className="text-3xl font-bold text-pink-600 mb-6 text-center">
            Registro
          </h1>

          <textarea
            value={nota}
            onChange={(e) => setNota(e.target.value)}
            placeholder="Escribe una observación o recordatorio..."
            className="w-full border border-gray-300 rounded-lg p-3 mb-4 placeholder-black text-black focus:outline-none focus:ring-2 focus:ring-pink-300"
          />

          <div className="mb-4">
            <label className="block text-gray-700 font-medium mb-1">
              Fecha de la nota
            </label>

            <input
              type="date"
              value={fecha}
              onChange={(e) => setFecha(e.target.value)}
              className="w-full border border-gray-300 rounded-lg p-2 text-black focus:outline-none focus:ring-2 focus:ring-pink-300"
            />
          </div>

          <button
            onClick={agregarNota}
            className="bg-pink-500 text-white px-6 py-2 rounded-lg mb-6 hover:bg-pink-600 transition-colors w-full"
          >
            Guardar nota
          </button>

          <div className="space-y-4">
            {notas.map((n, index) => (
              <div
                key={index}
                className="p-4 border rounded-lg bg-gray-50 shadow-sm"
              >
                <div className="text-gray-700">{n.texto}</div>
                <div className="text-xs text-gray-400 mt-1 text-right">{n.fecha}</div>
              </div>
            ))}
          </div>

        </div>

        {/* REPORTE FINAL */}
        <div className="space-y-6">

          {/* RESULTADO */}
          <div className="bg-green-100 border border-green-300 rounded-xl p-6 shadow text-center">
            <h2 className="text-xl font-bold text-green-700 mb-2">
              🌟 ¡Exelente! No se detectaron aspectos preocupantes
            </h2>

            <p className="text-green-700 font-medium ">
              Tu autoexamen no mostró hallazgos que requieran atención inmediata
            </p>
          </div>

          {/* RESUMEN */}
          <div className="bg-white border rounded-xl p-6 shadow">
            <h2 className="text-lg font-bold text-pink-600 mb-2">
              Resumen del autoexamen
            </h2>

            <ul className="list-disc list-inside text-gray-700 space-y-1">
              <li>(Aqui irá el texto generado por IA en el futuro...).</li>
            </ul>
          </div>

          {/* RECOMENDACIONES */}
          <div className="bg-white border rounded-xl p-6 shadow">
            <h2 className="text-lg font-bold text-pink-600 mb-2">
              Recomendaciones
            </h2>

            <ul className="list-disc list-inside text-gray-700 space-y-1">
              <li>Realizar el autoexamen de mama una vez al mes.</li>
              <li>Consultar con un médico ante cualquier cambio.</li>
            </ul>
          </div>

        </div>

      </div>

    </main>
  );
}