import Link from "next/link";
import Image from "next/image";

export default function Navbar() {
  return (
    <nav className="flex justify-between items-center bg-pink-200 px-6 py-4 border-b-4 border-pink-500">

      <div className="flex items-center space-x-3">
            {/* Imagen al lado del título */}
            <img
            src="/logo.png" // coloca la ruta de tu imagen aquí
            alt="Logo"
            width={50}
            height={50}
            className="rounded-lg"
            />

            <h1 className="text-4xl font-bold text-pink-600">
            Guíame
            </h1>
        </div>

      <div className="flex gap-6 text-xl">
        <Link href="/"><Image
        src="/Info.jpg"
        alt="Principal"
        className="rounded-full  border-pink-500"
        width={40}
        height={30}
        />
        </Link>
        <Link href="/Reporte"><Image
        src="/reporte.png"
        alt="reporte"
        className="rounded-full  border-pink-500"
        width={40}
        height={30}
        />
        </Link>
        <Link href="/perfil"><Image
        src="/download.jfif"
        alt="Inicio"
        className="rounded-full  border-pink-500"
        width={40}
        height={30}
        />
        </Link>
      </div>

    </nav>
  );
}