import { cookies } from "next/headers";
import Link from "next/link";

async function getStudentInfo() {
    const cookieStore = await cookies();
    const token = cookieStore.get("token")?.value;

    if (!token) {
        return null;
    }

    const response = await fetch("http://localhost:3000/students/me", {
        headers: { Authorization: `Bearer ${token}` },
        cache: "no-store"
    });

    return response.json();
}

export default async function StudentsPage() {
    const student = await getStudentInfo();

    return (
        <div>
            <header>
                <h1 className="text-2xl font-bold">Bem-vindo, {student?.name}</h1>
                <p className="text-gray-600">Email: {student?.email}</p>
            </header>
            <div className="grid grid-cols-2 gap-4 mt-8">
                <Link href="/classes/available" className="border rounded p-6 text-center cursor-pointer hover:bg-gray-50">Matricular Aulas</Link>
                <Link href="/students/enrollments" className="border rounded p-6 text-center cursor-pointer hover:bg-gray-50">Minhas Aulas Matriculadas</Link>
                <Link href="/students/plan" className="border rounded p-6 text-center cursor-pointer hover:bg-gray-50">Checar Plano</Link>
                <Link href="/students/me" className="border rounded p-6 text-center cursor-pointer hover:bg-gray-50">Minhas Informações</Link>
            </div>
        </div>
    );
}
