import { cookies } from "next/headers";
import EnrollButton from "./EnrollButton";

interface GymClass {
    id: string;
    typeClass: string;
    schedule: string;
    instructorId: string;
    studentsLimit: number;
    classStatus: string;
    enrolledCount: number;
}

async function fetchStudent(): Promise<{ id: string }> {
    const cookieStore = await cookies();
    const token = cookieStore.get("token")?.value;

    const response = await fetch("http://localhost:8080/students/me", {
        headers: { Authorization: `Bearer ${token}` },
        cache: "no-store"
    });

    return response.json();
}

async function fetchAvailableClasses(): Promise<GymClass[]> {
    const cookieStore = await cookies();
    const token = cookieStore.get("token")?.value;

    const response = await fetch("http://localhost:8080/students/classes/available", {
        headers: { Authorization: `Bearer ${token}` },
        cache: "no-store"
    });

    return response.json();
}

export default async function AvailableClassesPage() {
    const classes = await fetchAvailableClasses();
    const student = await fetchStudent();

    return (
        <div>
            <h1>Classes Available</h1>
            {classes.map((classe) => (
                <div key={classe.id}>
                    <div>{classe.typeClass}</div>
                    <div>{classe.schedule}</div>
                    <div>{classe.enrolledCount} of {classe.studentsLimit}</div>
                    <EnrollButton classId={classe.id} studentId={student.id} />
                </div>
            ))}
        </div>
    );
}
