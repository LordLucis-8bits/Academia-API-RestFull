import { cookies } from "next/headers";
import { redirect } from "next/navigation";
import ClassActionButton, { ClassStatus } from "../../classes/action/ClassActionButton";

interface GymClass {
    id: string;
    typeClass: string;
    schedule: string;
    classStatus: ClassStatus;
    studentsLimit: number;
    enrolledCount: number;
}

async function fetchInstructorClasses(): Promise<GymClass[]> {
    const cookieStore = await cookies();
    const token = cookieStore.get("token")?.value;
    
    if (!token) {        
        redirect("/login");
    }

    const response = await fetch("http://localhost:8080/classes/my-classes", {    
        headers: { Authorization: `Bearer ${token}` },
        cache: "no-store"
    });
  
    if (!response.ok) {   
        redirect("/login");
    }
        
    return response.json();
}

export default async function MyClassesPage() {
    const classes = await fetchInstructorClasses();

    return (
    <div>
        <h1>My Classes</h1>
        {classes.length === 0 ? ( <p>You have no classes yet.</p>
        ) : (
            classes.map((classe) => (
            <div key={classe.id}>
                <div>{classe.typeClass}</div>
                <div>{classe.schedule}</div>
                <div>{classe.enrolledCount} of {classe.studentsLimit}</div>
                <ClassActionButton classId={classe.id} initialStatus={classe.classStatus} />
            </div>
            ))
        )}
    </div>
    );
}
